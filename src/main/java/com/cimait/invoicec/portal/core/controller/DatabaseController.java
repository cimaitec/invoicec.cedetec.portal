package com.cimait.invoicec.portal.core.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.CustomerProperty;
import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentDetail;
import com.cimait.invoicec.core.entity.DocumentDetailProperty;
import com.cimait.invoicec.core.entity.DocumentProperty;
import com.cimait.invoicec.core.entity.DocumentType;
import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.entity.EmitterProperty;
import com.cimait.invoicec.core.entity.PropertyType;
import com.cimait.invoicec.core.repository.CustomerRepository;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.DocumentTypeRepository;
import com.cimait.invoicec.core.repository.DocumentTypeService;
import com.cimait.invoicec.core.repository.EmitterRepository;
import com.cimait.invoicec.core.repository.PropertyTypeRepository;


@Controller
public class DatabaseController {

		//CODE PROPERTIES DEL EMISOR
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
	
    	//CODE PROPERTIES DEL DOCUMENTO
	   private static final String PROPERTY_TYPE_FNAME = "FNAME";    		
	   private static final String PROPERTY_TYPE_UBIGEO = "UBIGEO";
	   private static final String PROPERTY_TYPE_DIR = "DIR";
	   private static final String PROPERTY_TYPE_DIRURB = "DIRURB";
	   private static final String PROPERTY_TYPE_DIRPRV = "DIRPRV";
	   private static final String PROPERTY_TYPE_DIRDEP = "DIRDEP";
	   private static final String PROPERTY_TYPE_DIRDIS = "DIRDIS";
	   private static final String PROPERTY_TYPE_DIRPAIS = "DIRPAIS";
	   private static final String PROPERTY_TYPE_TIPIDDOC = "TIPIDDOC";
	   private static final String PROPERTY_TYPE_TIPIDADQ = "TIPIDADQ";
	   private static final String PROPERTY_TYPE_RAZSOCADQ = "RAZSOCADQ";
	   private static final String PROPERTY_TYPE_UM = "UM";				//Unidad Medida
	   private static final String PROPERTY_TYPE_PUI = "PUI";				//Precio Venta Unitario Item
	   private static final String PROPERTY_TYPE_CUI = "CUI";				//Codigo Venta Unitario Item

	 //CODE PROPERTIES DEL CUSTOMER
	   private static final String PROPERTY_TYPE_TELEF = "TELEF"; 
	   private static final String PROPERTY_TYPE_HOLD = "HOLD";
	   
	   
		@Autowired
	    private EmitterRepository emitterRepository;
		@Autowired
	    protected CustomerRepository customerRepository;		
		@Autowired
	    private DocumentRepository documentRepository;		
	    @Autowired
	    protected PropertyTypeRepository propertyTypeRepository;	
	    @Autowired
	    protected DocumentTypeRepository documentTypeRepository;
	    @Autowired
	    protected DocumentTypeService documentTypeService;	   
	    
	    
	    
		@RequestMapping(method=RequestMethod.POST, value="/api/v1/emitteruno/save")
		@Transactional
		public void saveEmitterUnoDB(){
			Emitter emisor = new Emitter();
			emisor.setIdentification("20565812948");
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
			PropertyType typeTELEF = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TELEF);
			PropertyType typeHOLD = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_HOLD);
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
			CustomerProperty propTELEF = new CustomerProperty();
			propTELEF.setPropertyType(typeTELEF);
			propTELEF.setValue("234523452345");			
			CustomerProperty propHOLD = new CustomerProperty();
			propHOLD.setPropertyType(typeHOLD);
			propHOLD.setValue("Y");
			CustomerProperty propTELEF2 = new CustomerProperty();
			propTELEF2.setPropertyType(typeTELEF);
			propTELEF2.setValue("561-2001");			
			CustomerProperty propHOLD2 = new CustomerProperty();
			propHOLD2.setPropertyType(typeHOLD);
			propHOLD2.setValue("Y");			
			
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
			emisor.getDocumentTypes().add(factura);
			emisor.getDocumentTypes().add(notaCredito);
			emisor.getDocumentTypes().add(notaDebito);
			factura.getEmitters().add(emisor);
			notaCredito.getEmitters().add(emisor);
			notaDebito.getEmitters().add(emisor);
			Customer customer = new Customer();
			customer.setBusinessName("I & T  ELECTRIC S.A.C.");
			customer.setIdentification("20503423287");
			customer.setAddress("DIRCLI");
			customer.setEmail("EMAILCLI");
			customer.setType("C");
			customer.setName("I & T  ELECTRIC S.A.C.");
			customer.setActive(true);
			customer.getProperties().add(propTELEF);
			customer.getProperties().add(propHOLD);
			propTELEF.setCustomer(customer);
			propHOLD.setCustomer(customer);
			Customer customer2 = new Customer();
			customer2.setBusinessName("EDELNOR S.A.A");
			customer2.setIdentification("20269985900");
			customer2.setAddress("JR. CESAR LOPEZ ROJAS Nro 155, SAN MIGUEL");
			customer2.setEmail("mcortez@edelnor.com.pe");
			customer2.setType("C");
			customer2.setName("EDELNOR S.A.A");
			customer2.setActive(true);
			customer2.getProperties().add(propTELEF2);
			customer2.getProperties().add(propHOLD2);
			propTELEF2.setCustomer(customer2);
			propHOLD2.setCustomer(customer2);
			emisor.getCustomers().add(customer);
			emisor.getCustomers().add(customer2);
			customer.setEmitter(emisor);
			customer2.setEmitter(emisor);
			
			emitterRepository.save(emisor);
		}
		
		@RequestMapping(method=RequestMethod.POST, value="/api/v1/emitterdos/save")
		@Transactional
		public void saveEmitterDosDB(){
			Emitter emisor = new Emitter();
			emisor.setIdentification("20503423287");
			emisor.setName("TECH SERVICES CIMA");
			emisor.setBusinessName("");
			emisor.setAddress("AV SAN BORJA NORTE");
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
			PropertyType typeTELEF = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TELEF);
			PropertyType typeHOLD = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_HOLD);
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
			CustomerProperty propTELEF = new CustomerProperty();
			propTELEF.setPropertyType(typeTELEF);
			propTELEF.setValue("23-34322");			
			CustomerProperty propHOLD = new CustomerProperty();
			propHOLD.setPropertyType(typeHOLD);
			propHOLD.setValue("Y");
			CustomerProperty propTELEF2 = new CustomerProperty();
			propTELEF2.setPropertyType(typeTELEF);
			propTELEF2.setValue("455-2331");			
			CustomerProperty propHOLD2 = new CustomerProperty();
			propHOLD2.setPropertyType(typeHOLD);
			propHOLD2.setValue("Y");			
			
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
			emisor.getDocumentTypes().add(factura);
			emisor.getDocumentTypes().add(notaCredito);
			emisor.getDocumentTypes().add(notaDebito);
			factura.getEmitters().add(emisor);
			notaCredito.getEmitters().add(emisor);
			notaDebito.getEmitters().add(emisor);
			Customer customer = new Customer();
			customer.setBusinessName("HUEMURA S.A.C.");
			customer.setIdentification("20396466768");
			customer.setAddress("AV. ESPANIA¸ 2419 INT. 201, TRUJILLO, LA LIBERTAD, PERU");
			customer.setEmail("nhuemura@huemura.com.pe");
			customer.setType("P");
			customer.setName("HUEMURA S.A.C.");
			customer.setActive(true);
			customer.getProperties().add(propTELEF);
			customer.getProperties().add(propHOLD);
			propTELEF.setCustomer(customer);
			propHOLD.setCustomer(customer);			
			Customer customer2 = new Customer();
			customer2.setBusinessName("CAFFO ABANTO DALVENITH AURELIO");
			customer2.setIdentification("10439800491");
			customer2.setAddress("CAL. HUACA DEL SOL NRO. ---- URB. LA CAMPIA DE MOCHE LA LIBERTAD - TRUJILLO - MOCHE");
			customer2.setEmail("dcffa86@gmail.com");
			customer2.setType("P");
			customer2.setName("CAFFO ABANTO DALVENITH AURELIO");
			customer2.setActive(true);
			customer2.getProperties().add(propTELEF2);
			customer2.getProperties().add(propHOLD2);
			propTELEF2.setCustomer(customer2);
			propHOLD2.setCustomer(customer2);
			emisor.getCustomers().add(customer);
			emisor.getCustomers().add(customer2);
			customer.setEmitter(emisor);
			customer2.setEmitter(emisor);			
			emitterRepository.save(emisor);
		}
		
		/**
		 * Salvamos una Nota Credito, Document Type 07
		 */
		@RequestMapping(method=RequestMethod.POST, value="/api/v1/documentuno/save")
		@Transactional
		public void saveDocumentUno(){
			Date date= new Date();
			Timestamp timeNow =	new Timestamp(date.getTime());
			PropertyType typeFNAME = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_FNAME);
			PropertyType typeUBIGEO = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_UBIGEO);
			PropertyType typeDIR = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIR);
			PropertyType typeDIRURB = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRURB);
			PropertyType typeDIRPRV = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRPRV);
	 		PropertyType typeDIRDEP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRDEP);    		
	 		PropertyType typeDIRDIS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRDIS);
	 		PropertyType typeDIRPAIS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRPAIS);
	 		PropertyType typeTIPIDDOC = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TIPIDDOC);
	 		PropertyType typeTIPIDADQ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TIPIDADQ);
	 		PropertyType typeRAZSOCADQ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_RAZSOCADQ);
			PropertyType typeUM = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_UM);
	 		PropertyType typePUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PUI);
	 		PropertyType typeCUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CUI);
	 		Customer customer = customerRepository.findOne(new Long(1));
	 		
	 		
			Document doc = new Document();
			doc.setLegalNumber("F001-00000001");
			doc.setDocumentType(documentTypeRepository.findByTypeId("01"));
			Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date(System.currentTimeMillis()));
	        cal.add(Calendar.YEAR, -2);
	        doc.setIssueDate(cal.getTime());
			doc.setCurrency("USD");
			doc.setAmount(new Double(1000.00));
			doc.setActive(true);
			doc.setStatus("AT");
			doc.setSource("FS");
			doc.setCreatedDate(timeNow);
			doc.setCreatedUser("CORE");
			doc.setCustomer(customer);
	
			DocumentDetail det1 = new DocumentDetail();
			det1.setSequence("000001");
			det1.setCode("MP020007");
			det1.setDescr("ALAMBRON MUESTRA PRUEBAS");
			det1.setQuantity(new Double(2.0));
			det1.setAmount(new Double(5.00));
			det1.setCreatedDate(timeNow);
			det1.setCreatedUser("INSTALLATION");
			DocumentDetail det2 = new DocumentDetail();
			det2.setSequence("000003");
			det2.setCode("MP020008");
			det2.setDescr("ALAMBRON MUESTRA PRUEBAS 2");
			det2.setQuantity(new Double(5.0));
			det2.setAmount(new Double(345.2323));
			det2.setCreatedDate(timeNow);
			det2.setCreatedUser("INSTALLATION");    		
			
			DocumentProperty propFNAME = new DocumentProperty();
			propFNAME.setPropertyType(typeFNAME);
			propFNAME.setValueString("20565812948-RA-F001-00000001.txt");
			DocumentProperty propUBIGEO = new DocumentProperty();
			propUBIGEO.setPropertyType(typeUBIGEO);
			propUBIGEO.setValueString("150131");
			DocumentProperty propDIR = new DocumentProperty();
			propDIR.setPropertyType(typeDIR);
			propDIR.setValueString("CALLE LAS CAMELIAS Nº 877 DEPTO. # 702");
			DocumentProperty propDIRURB = new DocumentProperty();
			propDIRURB.setPropertyType(typeDIRURB);
			propDIRURB.setValueString("");
			DocumentProperty propDIRPRV = new DocumentProperty();
			propDIRPRV.setPropertyType(typeDIRPRV);
			propDIRPRV.setValueString("LIMA");
			DocumentProperty propDIRDEP = new DocumentProperty();
			propDIRDEP.setPropertyType(typeDIRDEP);
			propDIRDEP.setValueString("LIMA");
			DocumentProperty propDIRDIS = new DocumentProperty();
			propDIRDIS.setPropertyType(typeDIRDIS);
			propDIRDIS.setValueString("SAN ISIDRO");
			DocumentProperty propDIRPAIS = new DocumentProperty();
			propDIRPAIS.setPropertyType(typeDIRPAIS);
			propDIRPAIS.setValueString("PE");
			DocumentProperty propTIPIDDOC = new DocumentProperty();
			propTIPIDDOC.setPropertyType(typeTIPIDDOC);
			propTIPIDDOC.setValueString("6");
			DocumentProperty propTIPIDADQ = new DocumentProperty();
			propTIPIDADQ.setPropertyType(typeTIPIDADQ);
			propTIPIDADQ.setValueString("6");
			DocumentProperty propRAZSOCADQ = new DocumentProperty();
			propRAZSOCADQ.setPropertyType(typeRAZSOCADQ);
			propRAZSOCADQ.setValueString("EDELNOR S.A.A");
			DocumentDetailProperty propUM = new DocumentDetailProperty();
			propUM.setPropertyType(typeUM);
			propUM.setValueString("KGM");
			DocumentDetailProperty propPUI = new DocumentDetailProperty();
			propPUI.setPropertyType(typePUI);
			propPUI.setValueAmount(new Double(2.5));
			DocumentDetailProperty propCUI = new DocumentDetailProperty();
			propCUI.setPropertyType(typeCUI);
			propCUI.setValueString("01");
			det1.getProperties().add(propUM);
			det1.getProperties().add(propPUI);
			det1.getProperties().add(propCUI);
			propUM.setDocumentDetail(det1);
			propPUI.setDocumentDetail(det1);
			propCUI.setDocumentDetail(det1);
			doc.getDetails().add(det1);
			doc.getDetails().add(det2);  
			det1.setDocument(doc);
			det2.setDocument(doc);
			doc.getProperties().add(propFNAME);
			doc.getProperties().add(propUBIGEO);
			doc.getProperties().add(propDIR);
			doc.getProperties().add(propDIRURB);
			doc.getProperties().add(propDIRPRV);
			doc.getProperties().add(propDIRDEP);
			doc.getProperties().add(propDIRDIS);
			doc.getProperties().add(propDIRPAIS);
			doc.getProperties().add(propTIPIDDOC);
			doc.getProperties().add(propTIPIDADQ);
			doc.getProperties().add(propRAZSOCADQ);
			propFNAME.setDocument(doc);
			propUBIGEO.setDocument(doc);
			propDIR.setDocument(doc);
			propDIRURB.setDocument(doc);
			propDIRPRV.setDocument(doc);
			propDIRDEP.setDocument(doc);
			propDIRDIS.setDocument(doc);
			propDIRPAIS.setDocument(doc);
			propTIPIDDOC.setDocument(doc);
			propTIPIDADQ.setDocument(doc);
			propRAZSOCADQ.setDocument(doc);
			documentRepository.save(doc);
		}

	
		/**
		 * Salvamos una Boleta, Document Type 03
		 */
		@RequestMapping(method=RequestMethod.POST, value="/api/v1/documentdos/save")
		@Transactional
		public void saveDocumentDos(){
			Date date= new Date();
			Timestamp timeNow =	new Timestamp(date.getTime());
			PropertyType typeFNAME = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_FNAME);
			PropertyType typeUBIGEO = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_UBIGEO);
			PropertyType typeDIR = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIR);
			PropertyType typeDIRURB = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRURB);
			PropertyType typeDIRPRV = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRPRV);
	 		PropertyType typeDIRDEP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRDEP);    		
	 		PropertyType typeDIRDIS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRDIS);
	 		PropertyType typeDIRPAIS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRPAIS);
	 		PropertyType typeTIPIDDOC = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TIPIDDOC);
	 		PropertyType typeTIPIDADQ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TIPIDADQ);
	 		PropertyType typeRAZSOCADQ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_RAZSOCADQ);
			PropertyType typeUM = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_UM);
	 		PropertyType typePUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PUI);
	 		PropertyType typeCUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CUI);
	 		Customer customer = customerRepository.findOne(new Long(1));
	 		
			Document doc = new Document();
			doc.setLegalNumber("BG01-00000001");
			doc.setDocumentType(documentTypeRepository.findByTypeId("03"));
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date(System.currentTimeMillis()));
	        cal.add(Calendar.YEAR, -1);
	        doc.setIssueDate(cal.getTime());
			doc.setCurrency("USD");
			doc.setAmount(new Double(1110.00));
			doc.setActive(true);
			doc.setStatus("AT");
			doc.setSource("FS");
			doc.setCreatedDate(timeNow);
			doc.setCreatedUser("CORE");
			doc.setCustomer(customer);
	
			DocumentDetail det1 = new DocumentDetail();
			det1.setSequence("100909");
			det1.setCode("EA");
			det1.setDescr("Tornillo Hexagonal Métrico 8.8 Cuerdas Finas");
			det1.setQuantity(new Double(50.0));
			det1.setAmount(new Double(10.00));
			det1.setCreatedDate(timeNow);
			det1.setCreatedUser("INSTALLATION");
			DocumentDetail det2 = new DocumentDetail();
			det2.setSequence("100909");
			det2.setCode("EA");
			det2.setDescr("Tornillo Hexagonal Métrico 8.8 Cuerdas Finas");
			det2.setQuantity(new Double(60.0));
			det2.setAmount(new Double(10.23));
			det2.setCreatedDate(timeNow);
			det2.setCreatedUser("INSTALLATION");    		
			
			DocumentProperty propFNAME = new DocumentProperty();
			propFNAME.setPropertyType(typeFNAME);
			propFNAME.setValueString("20565812948-03-BG01-00000001.txt");
			DocumentProperty propUBIGEO = new DocumentProperty();
			propUBIGEO.setPropertyType(typeUBIGEO);
			propUBIGEO.setValueString("150131");
			DocumentProperty propDIR = new DocumentProperty();
			propDIR.setPropertyType(typeDIR);
			propDIR.setValueString("Av. Separadora Industrial 333");
			DocumentProperty propDIRURB = new DocumentProperty();
			propDIRURB.setPropertyType(typeDIRURB);
			propDIRURB.setValueString("");
			DocumentProperty propDIRPRV = new DocumentProperty();
			propDIRPRV.setPropertyType(typeDIRPRV);
			propDIRPRV.setValueString("LIMA");
			DocumentProperty propDIRDEP = new DocumentProperty();
			propDIRDEP.setPropertyType(typeDIRDEP);
			propDIRDEP.setValueString("LIMA");
			DocumentProperty propDIRDIS = new DocumentProperty();
			propDIRDIS.setPropertyType(typeDIRDIS);
			propDIRDIS.setValueString("ATE");
			DocumentProperty propDIRPAIS = new DocumentProperty();
			propDIRPAIS.setPropertyType(typeDIRPAIS);
			propDIRPAIS.setValueString("PE");
			DocumentProperty propTIPIDDOC = new DocumentProperty();
			propTIPIDDOC.setPropertyType(typeTIPIDDOC);
			propTIPIDDOC.setValueString("6");
			DocumentProperty propTIPIDADQ = new DocumentProperty();
			propTIPIDADQ.setPropertyType(typeTIPIDADQ);
			propTIPIDADQ.setValueString("6");
			DocumentProperty propRAZSOCADQ = new DocumentProperty();
			propRAZSOCADQ.setPropertyType(typeRAZSOCADQ);
			propRAZSOCADQ.setValueString("EDELNOR S.A.A");
			DocumentDetailProperty propUM = new DocumentDetailProperty();
			propUM.setPropertyType(typeUM);
			propUM.setValueString("KGM");
			DocumentDetailProperty propPUI = new DocumentDetailProperty();
			propPUI.setPropertyType(typePUI);
			propPUI.setValueAmount(new Double(2.5));
			DocumentDetailProperty propCUI = new DocumentDetailProperty();
			propCUI.setPropertyType(typeCUI);
			propCUI.setValueString("01");
			det1.getProperties().add(propUM);
			det1.getProperties().add(propPUI);
			det1.getProperties().add(propCUI);
			propUM.setDocumentDetail(det1);
			propPUI.setDocumentDetail(det1);
			propCUI.setDocumentDetail(det1);
			doc.getDetails().add(det1);
			doc.getDetails().add(det2);  
			det1.setDocument(doc);
			det2.setDocument(doc);
			doc.getProperties().add(propFNAME);
			doc.getProperties().add(propUBIGEO);
			doc.getProperties().add(propDIR);
			doc.getProperties().add(propDIRURB);
			doc.getProperties().add(propDIRPRV);
			doc.getProperties().add(propDIRDEP);
			doc.getProperties().add(propDIRDIS);
			doc.getProperties().add(propDIRPAIS);
			doc.getProperties().add(propTIPIDDOC);
			doc.getProperties().add(propTIPIDADQ);
			doc.getProperties().add(propRAZSOCADQ);
			propFNAME.setDocument(doc);
			propUBIGEO.setDocument(doc);
			propDIR.setDocument(doc);
			propDIRURB.setDocument(doc);
			propDIRPRV.setDocument(doc);
			propDIRDEP.setDocument(doc);
			propDIRDIS.setDocument(doc);
			propDIRPAIS.setDocument(doc);
			propTIPIDDOC.setDocument(doc);
			propTIPIDADQ.setDocument(doc);
			propRAZSOCADQ.setDocument(doc);
			documentRepository.save(doc);
		}


		/**
		 * Salvamos una FACTURA, Document Type 01
		 */
		@RequestMapping(method=RequestMethod.POST, value="/api/v1/documenttres/save")
		@Transactional
		public void saveDocumentTres(){
			Date date= new Date();
			Timestamp timeNow =	new Timestamp(date.getTime());
			PropertyType typeFNAME = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_FNAME);
			PropertyType typeUBIGEO = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_UBIGEO);
			PropertyType typeDIR = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIR);
			PropertyType typeDIRURB = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRURB);
			PropertyType typeDIRPRV = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRPRV);
	 		PropertyType typeDIRDEP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRDEP);    		
	 		PropertyType typeDIRDIS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRDIS);
	 		PropertyType typeDIRPAIS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRPAIS);
	 		PropertyType typeTIPIDDOC = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TIPIDDOC);
	 		PropertyType typeTIPIDADQ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TIPIDADQ);
	 		PropertyType typeRAZSOCADQ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_RAZSOCADQ);
			PropertyType typeUM = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_UM);
	 		PropertyType typePUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PUI);
	 		PropertyType typeCUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CUI);
	 		Customer customer = customerRepository.findOne(new Long(3));
	 		
	 		
			Document doc = new Document();
			doc.setLegalNumber("F002-00000423");
			doc.setDocumentType(documentTypeRepository.findByTypeId("07"));
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date(System.currentTimeMillis()));
	        cal.add(Calendar.YEAR, -1);
	        doc.setIssueDate(cal.getTime());
			doc.setCurrency("USD");
			doc.setAmount(new Double(4440.00));
			doc.setActive(true);
			doc.setStatus("AT");
			doc.setSource("FS");
			doc.setCreatedDate(timeNow);
			doc.setCreatedUser("CORE");
			doc.setCustomer(customer);
	
			DocumentDetail det1 = new DocumentDetail();
			det1.setSequence("00009794");
			det1.setCode("EA");
			det1.setDescr("Mesas Madera Cedro 2.80 X 3.40");
			det1.setQuantity(new Double(5.0));
			det1.setAmount(new Double(260.00));
			det1.setCreatedDate(timeNow);
			det1.setCreatedUser("INSTALLATION");
			DocumentDetail det2 = new DocumentDetail();
			det2.setSequence("00009795");
			det2.setCode("EA");
			det2.setDescr("Sillas Madera Cedro");
			det2.setQuantity(new Double(12.0));
			det2.setAmount(new Double(60.00));
			det2.setCreatedDate(timeNow);
			det2.setCreatedUser("INSTALLATION");    		
			
			DocumentProperty propFNAME = new DocumentProperty();
			propFNAME.setPropertyType(typeFNAME);
			propFNAME.setValueString("20565812948-07-F002-00000423.txt");
			DocumentProperty propUBIGEO = new DocumentProperty();
			propUBIGEO.setPropertyType(typeUBIGEO);
			propUBIGEO.setValueString("150131");
			DocumentProperty propDIR = new DocumentProperty();
			propDIR.setPropertyType(typeDIR);
			propDIR.setValueString("Av. Felipe Salaverry 333");
			DocumentProperty propDIRURB = new DocumentProperty();
			propDIRURB.setPropertyType(typeDIRURB);
			propDIRURB.setValueString("");
			DocumentProperty propDIRPRV = new DocumentProperty();
			propDIRPRV.setPropertyType(typeDIRPRV);
			propDIRPRV.setValueString("LIMA");
			DocumentProperty propDIRDEP = new DocumentProperty();
			propDIRDEP.setPropertyType(typeDIRDEP);
			propDIRDEP.setValueString("LIMA");
			DocumentProperty propDIRDIS = new DocumentProperty();
			propDIRDIS.setPropertyType(typeDIRDIS);
			propDIRDIS.setValueString("LINCE");
			DocumentProperty propDIRPAIS = new DocumentProperty();
			propDIRPAIS.setPropertyType(typeDIRPAIS);
			propDIRPAIS.setValueString("PE");
			DocumentProperty propTIPIDDOC = new DocumentProperty();
			propTIPIDDOC.setPropertyType(typeTIPIDDOC);
			propTIPIDDOC.setValueString("6");
			DocumentProperty propTIPIDADQ = new DocumentProperty();
			propTIPIDADQ.setPropertyType(typeTIPIDADQ);
			propTIPIDADQ.setValueString("6");
			DocumentProperty propRAZSOCADQ = new DocumentProperty();
			propRAZSOCADQ.setPropertyType(typeRAZSOCADQ);
			propRAZSOCADQ.setValueString("EDELNOR S.A.A");
			DocumentDetailProperty propUM = new DocumentDetailProperty();
			propUM.setPropertyType(typeUM);
			propUM.setValueString("KGM");
			DocumentDetailProperty propPUI = new DocumentDetailProperty();
			propPUI.setPropertyType(typePUI);
			propPUI.setValueAmount(new Double(2.5));
			DocumentDetailProperty propCUI = new DocumentDetailProperty();
			propCUI.setPropertyType(typeCUI);
			propCUI.setValueString("01");
			det1.getProperties().add(propUM);
			det1.getProperties().add(propPUI);
			det1.getProperties().add(propCUI);
			propUM.setDocumentDetail(det1);
			propPUI.setDocumentDetail(det1);
			propCUI.setDocumentDetail(det1);
			doc.getDetails().add(det1);
			doc.getDetails().add(det2);  
			det1.setDocument(doc);
			det2.setDocument(doc);
			doc.getProperties().add(propFNAME);
			doc.getProperties().add(propUBIGEO);
			doc.getProperties().add(propDIR);
			doc.getProperties().add(propDIRURB);
			doc.getProperties().add(propDIRPRV);
			doc.getProperties().add(propDIRDEP);
			doc.getProperties().add(propDIRDIS);
			doc.getProperties().add(propDIRPAIS);
			doc.getProperties().add(propTIPIDDOC);
			doc.getProperties().add(propTIPIDADQ);
			doc.getProperties().add(propRAZSOCADQ);
			propFNAME.setDocument(doc);
			propUBIGEO.setDocument(doc);
			propDIR.setDocument(doc);
			propDIRURB.setDocument(doc);
			propDIRPRV.setDocument(doc);
			propDIRDEP.setDocument(doc);
			propDIRDIS.setDocument(doc);
			propDIRPAIS.setDocument(doc);
			propTIPIDDOC.setDocument(doc);
			propTIPIDADQ.setDocument(doc);
			propRAZSOCADQ.setDocument(doc);
			documentRepository.save(doc);
		}
		

		/**
		 * Salvamos una NOTA DEBITO, Document Type 08
		 */
		@RequestMapping(method=RequestMethod.POST, value="/api/v1/documentcuatro/save")
		@Transactional
		public void saveDocumentCuatro(){
			Date date= new Date();
			Timestamp timeNow =	new Timestamp(date.getTime());
			PropertyType typeFNAME = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_FNAME);
			PropertyType typeUBIGEO = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_UBIGEO);
			PropertyType typeDIR = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIR);
			PropertyType typeDIRURB = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRURB);
			PropertyType typeDIRPRV = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRPRV);
	 		PropertyType typeDIRDEP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRDEP);    		
	 		PropertyType typeDIRDIS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRDIS);
	 		PropertyType typeDIRPAIS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DIRPAIS);
	 		PropertyType typeTIPIDDOC = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TIPIDDOC);
	 		PropertyType typeTIPIDADQ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TIPIDADQ);
	 		PropertyType typeRAZSOCADQ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_RAZSOCADQ);
			PropertyType typeUM = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_UM);
	 		PropertyType typePUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PUI);
	 		PropertyType typeCUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CUI);
	 		Customer customer = customerRepository.findOne(new Long(3));
	 		
	 		
	 		
			Document doc = new Document();
			doc.setLegalNumber("F002-00000173");
			doc.setDocumentType(documentTypeRepository.findByTypeId("08"));
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date(System.currentTimeMillis()));
	        cal.add(Calendar.YEAR, -2);
	        doc.setIssueDate(cal.getTime());
			doc.setCurrency("USD");
			doc.setAmount(new Double(220.00));
			doc.setActive(true);
			doc.setStatus("AT");
			doc.setSource("FS");
			doc.setCreatedDate(timeNow);
			doc.setCreatedUser("CORE");
			doc.setCustomer(customer);
	
			DocumentDetail det1 = new DocumentDetail();
			det1.setSequence("00009794");
			det1.setCode("EA");
			det1.setDescr("Laptops Dell Inspiron");
			det1.setQuantity(new Double(5.0));
			det1.setAmount(new Double(1260.00));
			det1.setCreatedDate(timeNow);
			det1.setCreatedUser("INSTALLATION");
			DocumentDetail det2 = new DocumentDetail();
			det2.setSequence("00009795");
			det2.setCode("EA");
			det2.setDescr("Monitores Dell 20 pulgadas");
			det2.setQuantity(new Double(3.0));
			det2.setAmount(new Double(160.00));
			det2.setCreatedDate(timeNow);
			det2.setCreatedUser("INSTALLATION");    		
			
			DocumentProperty propFNAME = new DocumentProperty();
			propFNAME.setPropertyType(typeFNAME);
			propFNAME.setValueString("20565812948-08-F002-00000173.txt");
			DocumentProperty propUBIGEO = new DocumentProperty();
			propUBIGEO.setPropertyType(typeUBIGEO);
			propUBIGEO.setValueString("131");
			DocumentProperty propDIR = new DocumentProperty();
			propDIR.setPropertyType(typeDIR);
			propDIR.setValueString("Av. Arequipa 3233");
			DocumentProperty propDIRURB = new DocumentProperty();
			propDIRURB.setPropertyType(typeDIRURB);
			propDIRURB.setValueString("");
			DocumentProperty propDIRPRV = new DocumentProperty();
			propDIRPRV.setPropertyType(typeDIRPRV);
			propDIRPRV.setValueString("LIMA");
			DocumentProperty propDIRDEP = new DocumentProperty();
			propDIRDEP.setPropertyType(typeDIRDEP);
			propDIRDEP.setValueString("LIMA");
			DocumentProperty propDIRDIS = new DocumentProperty();
			propDIRDIS.setPropertyType(typeDIRDIS);
			propDIRDIS.setValueString("SANTA BEATRIZ");
			DocumentProperty propDIRPAIS = new DocumentProperty();
			propDIRPAIS.setPropertyType(typeDIRPAIS);
			propDIRPAIS.setValueString("PE");
			DocumentProperty propTIPIDDOC = new DocumentProperty();
			propTIPIDDOC.setPropertyType(typeTIPIDDOC);
			propTIPIDDOC.setValueString("6");
			DocumentProperty propTIPIDADQ = new DocumentProperty();
			propTIPIDADQ.setPropertyType(typeTIPIDADQ);
			propTIPIDADQ.setValueString("6");
			DocumentProperty propRAZSOCADQ = new DocumentProperty();
			propRAZSOCADQ.setPropertyType(typeRAZSOCADQ);
			propRAZSOCADQ.setValueString("EDELNOR S.A.A");
			DocumentDetailProperty propUM = new DocumentDetailProperty();
			propUM.setPropertyType(typeUM);
			propUM.setValueString("KGM");
			DocumentDetailProperty propPUI = new DocumentDetailProperty();
			propPUI.setPropertyType(typePUI);
			propPUI.setValueAmount(new Double(2.5));
			DocumentDetailProperty propCUI = new DocumentDetailProperty();
			propCUI.setPropertyType(typeCUI);
			propCUI.setValueString("01");
			det1.getProperties().add(propUM);
			det1.getProperties().add(propPUI);
			det1.getProperties().add(propCUI);
			propUM.setDocumentDetail(det1);
			propPUI.setDocumentDetail(det1);
			propCUI.setDocumentDetail(det1);
			doc.getDetails().add(det1);
			doc.getDetails().add(det2);  
			det1.setDocument(doc);
			det2.setDocument(doc);
			doc.getProperties().add(propFNAME);
			doc.getProperties().add(propUBIGEO);
			doc.getProperties().add(propDIR);
			doc.getProperties().add(propDIRURB);
			doc.getProperties().add(propDIRPRV);
			doc.getProperties().add(propDIRDEP);
			doc.getProperties().add(propDIRDIS);
			doc.getProperties().add(propDIRPAIS);
			doc.getProperties().add(propTIPIDDOC);
			doc.getProperties().add(propTIPIDADQ);
			doc.getProperties().add(propRAZSOCADQ);
			propFNAME.setDocument(doc);
			propUBIGEO.setDocument(doc);
			propDIR.setDocument(doc);
			propDIRURB.setDocument(doc);
			propDIRPRV.setDocument(doc);
			propDIRDEP.setDocument(doc);
			propDIRDIS.setDocument(doc);
			propDIRPAIS.setDocument(doc);
			propTIPIDDOC.setDocument(doc);
			propTIPIDADQ.setDocument(doc);
			propRAZSOCADQ.setDocument(doc);
			documentRepository.save(doc);
		}

		
		
		
		
		
		
		@RequestMapping(method=RequestMethod.POST, value="/api/v1/customer/save/cliente")		
		public void saveCliente(@RequestBody Customer customer, HttpServletRequest request){
				System.out.println("Hace el llamado de grabado " +  customer.getIdentification());
		}
		
		/**
		@RequestMapping(method=RequestMethod.POST, value="/api/v1/role")
		public void saveRole(@RequestBody Role role, HttpServletRequest request){
			Role tmpRole = roleRepository.findOne(role.getCodRol());
			try {
			if (tmpRole != null) {
				tmpRole.setDescripcion(role.getDescripcion());
				tmpRole.setIsActive(role.getIsActive());
				roleRepository.save(tmpRole);
			} else {
				tmpRole = new Role();
				tmpRole.setCodRol(role.getCodRol());
				tmpRole.setDescripcion(role.getDescripcion());
				tmpRole.setIsActive(role.getIsActive());
				roleRepository.save(tmpRole);
			}
			} catch (Exception e) {
				System.out.println("Error al grabar role " + role.getCodRol());
				//throw new RoleInfoException();
			}
				
		}**/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
