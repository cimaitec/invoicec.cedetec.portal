package com.cimait.invoicec.core.repository;





import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cimait.invoicec.core.entity.DocumentType;
import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.entity.EmitterProperty;
import com.cimait.invoicec.core.entity.PropertyType;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-ctx.xml"})
public class EmitterRepositoryTest {

	private static final String PROPERTY_TYPE_PGEN = "PGEN";
    private static final String PROPERTY_TYPE_PSIG = "PSIG";
    private static final String PROPERTY_TYPE_PAUT = "PAUT";
    private static final String PROPERTY_TYPE_PREJ = "PREJ";
    private static final String PROPERTY_TYPE_PUPL = "PUPL";
    private static final String PROPERTY_TYPE_PTMP = "PTMP";
    private static final String PROPERTY_TYPE_PREC = "PREC";
    private static final String PROPERTY_TYPE_PJAS = "PJAS";
    private static final String PROPERTY_TYPE_PARC = "PARC";
    private static final String PROPERTY_TYPE_CERT = "CERT";
    private static final String DOCUMENT_TYPE_FACTURA = "01";
    private static final String DOCUMENT_TYPE_BOLETA = "03";
    private static final String DOCUMENT_TYPE_NCREDITO = "07";
	
	@Autowired
    private EmitterRepository emitterRepository;

    @Autowired
    protected PropertyTypeRepository propertyTypeRepository;

    @Autowired
    protected DocumentTypeRepository documentTypeRepository;	   
	
    @Autowired
    protected DocumentTypeService documentTypeService;	   
	
    
    @Before
    public void setUp() {}
 
    @Test
   public void testSaveDoc() {
    	/**
    	DocumentType doc = new DocumentType();
    	doc.setDescr("JOLETE");
    	doc.setTypeId("29");
    	documentTypeRepository.save(doc);**/
    }   

    
    @Test
    @Transactional
    public void testSave() {
    	try {
    		Emitter emisor = new Emitter();
    		emisor.setIdentification("20565812922");
    		emisor.setName("CENTELSA PERU SAC");
    		emisor.setBusinessName("");
    		emisor.setAddress("CALLE LAS CAMELIAS Nro 877 DEPTO. # 702");
    		emisor.setSchedId(new Long(0));
    		emisor.setCountryId("PE");
    		emisor.setArchive(true);
    		emisor.setArchiveTime(1);
    		emisor.setArchiveTypeTime("Y");
    		emisor.setActive(true);
    		emisor.setCertificateInDb(true);
    		emisor.setCertificateFile(null);
    		emisor.setCreatedDate(null);
    		emisor.setCreatedUser("INSTALATION");
    		PropertyType typePGEN = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PGEN);
    		PropertyType typePSIG = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PSIG);
    		PropertyType typePAUT = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PAUT);
    		PropertyType typePREJ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PREJ);
    		PropertyType typePUPL = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PUPL);
    		PropertyType typePTMP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PTMP);
    		PropertyType typePREC = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PREC);
    		PropertyType typePJAS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PJAS);
    		PropertyType typePARC = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PARC);
    		PropertyType typeCERT = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERT);    		
    		DocumentType factura = documentTypeService.findByTypeId(DOCUMENT_TYPE_FACTURA);
    		DocumentType notaCredito = documentTypeService.findByTypeId(DOCUMENT_TYPE_NCREDITO);
    		DocumentType notaDebito = documentTypeService.findByTypeId(DOCUMENT_TYPE_BOLETA);
    		
    		EmitterProperty propPGEN = new EmitterProperty();
    		propPGEN.setPropertyType(typePGEN);
    		propPGEN.setValue("c:\\mario\\invoice\\repository\\01-generated\\");
    		
    		EmitterProperty propPSIG = new EmitterProperty();
    		propPSIG.setPropertyType(typePSIG);
    		propPSIG.setValue("c:\\mario\\invoice\\repository\\03-signed\\");
    		
    		EmitterProperty propPAUT = new EmitterProperty();
    		propPAUT.setPropertyType(typePAUT);
    		propPAUT.setValue("c:\\mario\\invoice\\repository\\04-authorized\\");
    		
    		EmitterProperty propPREJ = new EmitterProperty();
    		propPREJ.setPropertyType(typePREJ);
    		propPREJ.setValue("c:\\mario\\invoice\\repository\\06-rejected\\");
    		
    		EmitterProperty propPUPL = new EmitterProperty();
    		propPUPL.setPropertyType(typePUPL);
    		propPUPL.setValue("c:\\mario\\invoice\\repository\\07-upload\\");
    		
    		EmitterProperty propPJAS = new EmitterProperty();
    		propPJAS.setPropertyType(typePJAS);
    		propPJAS.setValue("c:\\mario\\invoice\\repository\\cfg\\jasper\\");
    		
    		EmitterProperty propPREC = new EmitterProperty();
    		propPREC.setPropertyType(typePREC);
    		propPREC.setValue("c:\\mario\\invoice\\repository\\02-received\\");
    		
    		
    		EmitterProperty propPARC = new EmitterProperty();
    		propPARC.setPropertyType(typePARC);
    		propPARC.setValue("c:\\mario\\invoice\\repository\\archived\\");
    		
    		EmitterProperty propCERT = new EmitterProperty();
    		propCERT.setPropertyType(typeCERT);
    		propCERT.setValue("JKS");
    		
    		emisor.getDocumentTypes().add(factura);
    		emisor.getDocumentTypes().add(notaCredito);
    		emisor.getDocumentTypes().add(notaDebito);
    		factura.getEmitters().add(emisor);
    		notaCredito.getEmitters().add(emisor);
    		notaDebito.getEmitters().add(emisor);
    		
    		emisor.getProperties().add(propPGEN);
    		emisor.getProperties().add(propPSIG);
    		emisor.getProperties().add(propPAUT);
    		emisor.getProperties().add(propPREJ);
    		emisor.getProperties().add(propPUPL);
    		emisor.getProperties().add(propPJAS);
    		emisor.getProperties().add(propPREC);
    		emisor.getProperties().add(propPARC);
    		emisor.getProperties().add(propCERT);
    		
    		propPGEN.setEmitter(emisor);
    		propPSIG.setEmitter(emisor);
    		propPAUT.setEmitter(emisor);
    		propPREJ.setEmitter(emisor);
    		propPUPL.setEmitter(emisor);
    		propPJAS.setEmitter(emisor);
    		propPREC.setEmitter(emisor);
    		propPARC.setEmitter(emisor);
    		propCERT.setEmitter(emisor);
    		emitterRepository.save(emisor);
    	} catch(Exception e) {e.printStackTrace();}     
    	
    }   
    
    
    
   
}
