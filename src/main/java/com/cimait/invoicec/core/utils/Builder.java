package com.cimait.invoicec.core.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentDetail;
import com.cimait.invoicec.core.entity.DocumentDetailProperty;
import com.cimait.invoicec.core.entity.DocumentProperty;
import com.cimait.invoicec.core.entity.DocumentType;
import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.entity.EmitterProperty;
import com.cimait.invoicec.core.entity.PropertyType;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.DocumentTypeRepository;
import com.cimait.invoicec.core.repository.EmitterPropertyRepository;
import com.cimait.invoicec.core.repository.EmitterRepository;
import com.cimait.invoicec.core.repository.PropertyTypeRepository;


@Component
public class Builder {

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
	    private static final String PROPERTY_TYPE_CERIDS = "CERIDS";
	    /**private static final String PROPERTY_TYPE_SMTPH = "SMTPH";
	    private static final String PROPERTY_TYPE_SMTPP = "SMTPP";
	    private static final String PROPERTY_TYPE_SMTPS = "SMTPS";
	    private static final String PROPERTY_TYPE_EMAILS = "EMAILS";
	    private static final String PROPERTY_TYPE_EMAILSU = "EMAILSU";
	    private static final String PROPERTY_TYPE_EMAILSP = "EMAILSP";
	    private static final String PROPERTY_TYPE_EMAILR = "EMAILR";
	    private static final String PROPERTY_TYPE_EMAILRU = "EMAILRU";
	    private static final String PROPERTY_TYPE_EMAILRP = "EMAILRP";
	    private static final String PROPERTY_TYPE_CERPATH = "CERPATH";	    
	    private static final String PROPERTY_TYPE_CERSTU = "CERSTU";
	    private static final String PROPERTY_TYPE_CERSTP = "CERSTP";
	    private static final String PROPERTY_TYPE_CERU = "CERU";
	    private static final String PROPERTY_TYPE_CERP = "CERP";
	    private static final String PROPERTY_TYPE_CERIDS = "CERIDS";
	    private static final String PROPERTY_TYPE_CERISIG = "CERISIG";
	    private static final String PROPERTY_TYPE_TELEF = "TELEF";
	    private static final String PROPERTY_TYPE_HOLD = "HOLD";
	    private static final String PROPERTY_TYPE_FORF = "FORF";
	    private static final String PROPERTY_TYPE_FORR = "FORR";**/
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

	   @Autowired
	   protected EmitterRepository emitterRepository;
	   @Autowired
	   protected PropertyTypeRepository propertyTypeRepository;
	   @Autowired
	   protected EmitterPropertyRepository emitterPropertyRepository;
	   @Autowired
	   protected DocumentTypeRepository documentTypeRepository;	   
	   @Autowired
	   protected DocumentRepository documentRepository;
	
	/**
	 *  Se requiere que sea transaccional porque recupera DocumentTypes y accede a su coleccion de Emitters
	 *  para establecer la relacion ManyToMany  
	 * @return emitter
	 */
	@Transactional
	public Emitter buildDefaultEmitter(){
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
		//Agregado MM
		PropertyType typeCERIDS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERIDS);
		
		/**PropertyType typeSMTPH = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SMTPH);
		PropertyType typeSMTPP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SMTPP);
		PropertyType typeSMTPS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SMTPS);
		PropertyType typeEMAILS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILS);
		PropertyType typeEMAILSU = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILSU);
		PropertyType typeEMAILSP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILSP);
		PropertyType typeEMAILR = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILR);
		PropertyType typeEMAILRU = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILRU);
		PropertyType typeEMAILRP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILRP);
		PropertyType typeCERPATH = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERPATH);		
		PropertyType typeCERSTU = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERSTU);
		PropertyType typeCERSTP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERSTP);
		PropertyType typeCERU = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERU);
		PropertyType typeCERP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERP);
		PropertyType typeCERIDS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERIDS);
		PropertyType typeCERISIG = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERISIG);
		PropertyType typeTELEF = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TELEF);
		PropertyType typeHOLD = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_HOLD);
		PropertyType typeFORF = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_FORF);
		PropertyType typeFORR = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_FORR);**/
		DocumentType factura = documentTypeRepository.findByTypeId(DOCUMENT_TYPE_FACTURA);
		DocumentType notaCredito = documentTypeRepository.findByTypeId(DOCUMENT_TYPE_BOLETA);
		DocumentType notaDebito = documentTypeRepository.findByTypeId(DOCUMENT_TYPE_NCREDITO);
		
		
		EmitterProperty propPGEN = new EmitterProperty();
		propPGEN.setPropertyType(typePGEN);
		//propPGEN.setValue("c:\\mario\\invoice\\repository\\01-generated\\");
		propPGEN.setValue("c:\\Documento");
		
		EmitterProperty propPSIG = new EmitterProperty();
		propPSIG.setPropertyType(typePSIG);
		//propPSIG.setValue("c:\\mario\\invoice\\repository\\03-signed\\");
		propPSIG.setValue("c:\\Documento\\");
		
		EmitterProperty propPAUT = new EmitterProperty();
		propPAUT.setPropertyType(typePAUT);
		//propPAUT.setValue("c:\\mario\\invoice\\repository\\04-authorized\\");
		propPAUT.setValue("c:\\Documento\\");
		
		EmitterProperty propPREJ = new EmitterProperty();
		propPREJ.setPropertyType(typePREJ);
		//propPREJ.setValue("c:\\mario\\invoice\\repository\\06-rejected\\");
		propPREJ.setValue("c:\\Documento\\");
		
		EmitterProperty propPUPL = new EmitterProperty();
		propPUPL.setPropertyType(typePUPL);
		//propPUPL.setValue("c:\\mario\\invoice\\repository\\07-upload\\");
		propPUPL.setValue("c:\\Documento\\");
		
		EmitterProperty propPJAS = new EmitterProperty();
		propPJAS.setPropertyType(typePJAS);
		//propPJAS.setValue("c:\\mario\\invoice\\repository\\cfg\\jasper\\");
		propPJAS.setValue("c:\\Documento\\");
		
		EmitterProperty propPREC = new EmitterProperty();
		propPREC.setPropertyType(typePREC);
		//propPREC.setValue("c:\\mario\\invoice\\repository\\02-received\\");
		propPREC.setValue("c:\\Documento\\");
		
		
		EmitterProperty propPARC = new EmitterProperty();
		propPARC.setPropertyType(typePARC);
		//propPARC.setValue("c:\\mario\\invoice\\repository\\archived\\");
		propPARC.setValue("c:\\Documento\\");
		
		EmitterProperty propCERT = new EmitterProperty();
		propCERT.setPropertyType(typeCERT);
		propCERT.setValue("JKS");
		
		//Agreagdo MM
		EmitterProperty propCERIDS = new EmitterProperty();
		propCERIDS.setPropertyType(typeCERIDS);
		//propPARC.setValue("c:\\mario\\invoice\\repository\\archived\\");
		propCERIDS.setValue("IDSignCTLSA");
		
		
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
		emisor.getProperties().add(propCERIDS);
		
		
		propPGEN.setEmitter(emisor);
		propPSIG.setEmitter(emisor);
		propPAUT.setEmitter(emisor);
		propPREJ.setEmitter(emisor);
		propPUPL.setEmitter(emisor);
		propPJAS.setEmitter(emisor);
		propPREC.setEmitter(emisor);
		propPARC.setEmitter(emisor);
		propCERT.setEmitter(emisor);
		//Agregado MM
		propCERIDS.setEmitter(emisor);
		return emitterRepository.save(emisor);
	}
	
	public void buildDefaultDocument(){
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
 		
		Document doc = new Document();
		doc.setLegalNumber("F002-00000001");
		//doc.setDocTypeId(new Long(3));
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
		propFNAME.setValueString("20503423287-07-F002-00000423.txt");
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
	
}
