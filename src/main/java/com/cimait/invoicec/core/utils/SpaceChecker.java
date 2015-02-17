package com.cimait.invoicec.core.utils;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimait.invoicec.core.config.GlobalConfig;
import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.entity.EmitterProperty;
import com.cimait.invoicec.core.repository.EmitterPropertyRepository;




@Service
public class SpaceChecker {
	
	private static final long HEADER_TUPLE = 101;
	private static final long DETAIL_TUPLE = 133;
	private static final long HEADER_PROPERTY_TUPLE = 60;
	private static final long DETAIL_PROPERTY_TUPLE = 52;
	private static final long DETAIL_TUPLE_AVERAGE = 3;
	private static final long HEADER_PROPERTY_TUPLE_AVERAGE = 25;
	private static final long DETAIL_PROPERTY_TUPLE_AVERAGE = 5;
	
	@Autowired
	EmitterPropertyRepository emitterPropertyRepository;
	
	@Autowired
	GlobalConfig globalConfig;
	

	public boolean simpleSpaceCheck(Emitter emitter){
			double spaceNeeded = filesSpace(emitter) + databaseRecordsSpace(emitter);					
			return (spaceNeeded < driveSpace(emitter));
	}
		
	public boolean complexSpaceCheck(Emitter emitter){
			double spaceNeeded = filesSpace(emitter) + databaseRecordsSpace(emitter);
			spaceNeeded = spaceNeeded + percentageMonth(emitter);
			return (spaceNeeded < driveSpace(emitter));
	}	

	private double driveSpace(Emitter emitter){		
						EmitterProperty pathReceived = emitterPropertyRepository.findByCode(emitter.getId(), EmitterPropertyRepository.PROPERTY_TYPE_PATH_RECEIVED);
						String path = pathReceived.getValue();
					    String driveName = getDriveName(path);
					    File[] roots = File.listRoots();					    
					    long bytes = 0;
					    for (int index = 0; index < roots.length; index++) {  
							        String root = roots[index].toString();
							        if(root.equalsIgnoreCase(driveName)){
							                   bytes = roots[index].getUsableSpace();					                    
							        }
					    }
					    final long divider = 1024;
				    	double result = (double) bytes / (double) divider;				    	
				    	return result;	
    }
	
	
	private double filesSpace(Emitter emitter){
				File[] files = getReceivedFiles(emitter);
				long totalSize = 0;
				for(int i = 0; i < files.length; i++){
							totalSize += files[i].length();			
				}
			    final long divider = 1024;
		    	double result = (double) totalSize / (double) divider;				    	
		    	return result;						
	}	

	
	/** Usamos el modulo pgstattuple para calcular el tamaño de los registros en la BD 
		CREATE EXTENSION pgstattuple //500 + 1500 + 260
		select * from pgstattuple('iv_doc_hdr'); --> tuple_len/tuple_count = 101 bytes
		select * from pgstattuple('iv_doc_hdr_prop'); --> tuple_len/tuple_count = 60 bytes * 25(promedio properties por doc) = 1500 bytes
		select * from pgstattuple('iv_doc_dtl'); --> tuple_len/tuple_count = 133 bytes * 3(promedio detalles por doc) = 399 bytes
		select * from pgstattuple('iv_doc_dtl_prop'); --> tuple_len/tuple_count = 52 bytes * 3(promedio detalles por doc) = 156 bytes
	**/
	private double databaseRecordsSpace(Emitter emitter){
				long spacePerDocument = HEADER_TUPLE +  (DETAIL_TUPLE * DETAIL_TUPLE_AVERAGE) + ( HEADER_PROPERTY_TUPLE * HEADER_PROPERTY_TUPLE_AVERAGE) + (DETAIL_PROPERTY_TUPLE * DETAIL_PROPERTY_TUPLE_AVERAGE);
				double spacePerDocumentKb = (double)spacePerDocument/(double)1024;				
				File[] files = getReceivedFiles(emitter);
				return spacePerDocumentKb * files.length;				
	}
	
	private File[] getReceivedFiles(Emitter emitter){
				EmitterProperty pathReceived = emitterPropertyRepository.findByCode(emitter.getId(), EmitterPropertyRepository.PROPERTY_TYPE_PATH_RECEIVED);
				File directory = new File(pathReceived.getValue());
				return directory.listFiles();				
	}

	private String getDriveName(String path){
				String driveName = "";
				String inicio = path.substring(0, 2);
				if(inicio.contains(":")){ //es una maquina windows
							driveName = path.substring(0, 3);
				} else { //es linux
							driveName = path.substring(0, 1);
				}		
				return driveName;
	}
	
	private long percentageMonth(Emitter emitter){
				//TODO need functional spec
			return 0;
	}
	
	@PostConstruct	
	public void checkSpace(){			
				//simpleSpaceCheck(globalConfig.getEmitter());
	}
	
}