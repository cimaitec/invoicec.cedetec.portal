package com.cimait.invoicec.core.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimait.invoicec.core.config.GlobalConfig;
import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentArchive;
import com.cimait.invoicec.core.entity.DocumentDetail;
import com.cimait.invoicec.core.entity.DocumentDetailArchive;
import com.cimait.invoicec.core.entity.DocumentDetailProperty;
import com.cimait.invoicec.core.entity.DocumentDetailPropertyArchive;
import com.cimait.invoicec.core.entity.DocumentProperty;
import com.cimait.invoicec.core.entity.DocumentPropertyArchive;
import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.entity.EmitterProperty;
import com.cimait.invoicec.core.repository.DocumentArchiveRepository;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.DocumentService;
import com.cimait.invoicec.core.repository.EmitterPropertyRepository;
import com.cimait.invoicec.core.repository.EmitterRepository;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


@Service
public class Archiver {
	
	private static final String DOCUMENT_STATUS_AT = "AT";
	private static final String PROPERTY_TYPE_FNAME = "FNAME";
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	DocumentArchiveRepository documentArchiveRepository;

	@Autowired
	EmitterRepository emitterRepository;
	
	@Autowired
	EmitterPropertyRepository emitterPropertyRepository;
	
	@Autowired
	GlobalConfig globalConfig;
	
	@Autowired
	private DocumentService documentService;


	public void archive(Emitter emitter, String sourceDirectory, String destDirectory, FilenameFilter filter){				
				File directory = new File(sourceDirectory);
			    File[] toArchive =  directory.listFiles(filter);
				File archived = null;
				for(int i = 0; i < toArchive.length; i++){
							archived	=	toArchive[i];							
							archived.renameTo(new File(destDirectory + archived.getName()));
				}				
	}
	
	public void archiveFS(Emitter emitter){		
			EmitterProperty pathAutorizados = emitterPropertyRepository.findByCode(emitter.getId(), EmitterPropertyRepository.PROPERTY_TYPE_PATH_AUTHORIZED);
			EmitterProperty pathArchivados = emitterPropertyRepository.findByCode(emitter.getId(), EmitterPropertyRepository.PROPERTY_TYPE_PATH_ARCHIVED);
			final List<String> docsToArchive = archiveDB(emitter);			
			FilenameFilter filter = new FilenameFilter() {
			      public boolean accept(File dir, String name) {
			    	  		return docsToArchive.contains(name);			    	  
			      }
			  };
			archive(emitter, pathAutorizados.getValue(), pathArchivados.getValue(), filter);			
	}
	
	
	public List<String> archiveDB(Emitter emitter){
		List<String> docNamesToArchive = new ArrayList<String>();
		try {		
				Date fechaArchivado = getDateToArchive(emitter.getArchiveTypeTime(), emitter.getArchiveTime());
				List<Document> docs = documentService.findBeforeDate(DOCUMENT_STATUS_AT, fechaArchivado);
				Predicate<DocumentProperty> isFileName = new Predicate<DocumentProperty>() {
				       public boolean apply(DocumentProperty prop) {
				            return prop.getPropertyType().getTypeId().equalsIgnoreCase(PROPERTY_TYPE_FNAME);			        	
				        }               
				};
				Iterator<Document> i = docs.iterator();
				Document doc = null;
				DocumentArchive docArchive = null;
				while(i.hasNext()){
						doc = (Document)i.next();	
						DocumentProperty fileNameProperty = Iterables.find(doc.getProperties(), isFileName);
						docNamesToArchive.add(fileNameProperty.getValueString());
						docArchive = copyDocument(doc);
						documentArchiveRepository.save(docArchive);	
						documentRepository.delete(doc);
				}			
		}		
		catch(Exception e) { e.printStackTrace();}
		return docNamesToArchive;
	}
	
	
	
	public DocumentArchive copyDocument(Document doc){		
		DocumentArchive docArchive = new DocumentArchive();
		BeanUtils.copyProperties(doc, docArchive, new String[]{"details", "properties"});  //Copiamos el Documento
	  	List<DocumentProperty> propertiesSource = new ArrayList<DocumentProperty>(doc.getProperties());
		List<DocumentPropertyArchive> propertiesTarget = new ArrayList<DocumentPropertyArchive>();
		for (int i = 0; i < propertiesSource.size(); i++) {
			 		propertiesTarget.add(i, new DocumentPropertyArchive());
			 		BeanUtils.copyProperties(propertiesSource.get(i), propertiesTarget.get(i));  //Copiamos las propiedades del documento
			 		propertiesTarget.get(i).setDocument(docArchive);
		}
		docArchive.setProperties(propertiesTarget);
		
		List<DocumentDetailProperty> detailPropertySources = null;			
		List<DocumentDetail> detailsSource = new ArrayList<DocumentDetail>(doc.getDetails());
		List<DocumentDetailArchive> detailsTarget = new ArrayList<DocumentDetailArchive>();
		for (int i = 0; i < detailsSource.size(); i++) {						
					detailsTarget.add(i, new DocumentDetailArchive());
			 		BeanUtils.copyProperties(detailsSource.get(i), detailsTarget.get(i), new String[]{"properties", "document"}); //Copiamos Los Detalles del Documento
			 		detailsTarget.get(i).setDocument(docArchive);				 						 		
			 		detailPropertySources = new ArrayList<DocumentDetailProperty>(detailsSource.get(i).getProperties());
			 		List<DocumentDetailPropertyArchive> detailPropertyTarget = new ArrayList<DocumentDetailPropertyArchive>();
			 		for (int j = 0; j < detailPropertySources.size(); j++) {
			 					detailPropertyTarget.add(j, new DocumentDetailPropertyArchive());
			 					BeanUtils.copyProperties(detailPropertySources.get(j), detailPropertyTarget.get(j)); //copiamos las Propiedades del Detalle
			 					detailPropertyTarget.get(j).setDocumentDetail(detailsTarget.get(i));
			 		}
			 		detailsTarget.get(i).setProperties(detailPropertyTarget);
		}
		docArchive.setDetails(detailsTarget);
		return docArchive;
	}
		
	public Date getDateToArchive(String tipo, int duracion){
			int duracionNegativa = 0 - duracion;
			GregorianCalendar fecha = new GregorianCalendar();
			if(tipo.equalsIgnoreCase("Y")){ fecha.roll(Calendar.YEAR, duracionNegativa); }
			if(tipo.equalsIgnoreCase("M")){ fecha.roll(Calendar.MONTH, duracionNegativa); }
			if(tipo.equalsIgnoreCase("D")){ fecha.roll(Calendar.DAY_OF_YEAR, duracionNegativa);}
			return fecha.getTime();
	}
	
	@PostConstruct	
	public void archiver(){			
			//archiveFS(globalConfig.getEmitter());
	}

}
