package com.cimait.invoicec.portal.core.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentDetail;
import com.cimait.invoicec.core.entity.DocumentDetailProperty;
import com.cimait.invoicec.core.entity.DocumentLog;
import com.cimait.invoicec.core.entity.DocumentProperty;
import com.cimait.invoicec.core.entity.PropertyType;
import com.cimait.invoicec.core.repository.CustomerRepository;
import com.cimait.invoicec.core.repository.DocumentLogRepository;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.DocumentTypeRepository;
import com.cimait.invoicec.core.repository.PropertyTypeRepository;
import com.cimait.invoicec.portal.core.dto.DocumentDto;

@Controller
public class DocumentInputController {

	@Autowired
	protected DocumentRepository documentRepository;
	
	@Autowired
	protected CustomerRepository customerRepository;
	
	 @Autowired
	    protected DocumentTypeRepository documentTypeRepository;
	 
	 @Autowired
	    protected PropertyTypeRepository propertyTypeRepository;
	 
	 @Autowired
	 	protected DocumentLogRepository documentLogRepository;
	 
	 
	private static final String PROPERTY_TYPE_TIPOTRDOC = "TIPOTRDOC";//tipo documento relacionado   		
	private static final String PROPERTY_TYPE_NROGUIAREMI = "NROGUIAREMI"; //numero de guia de remision
	private static final String PROPERTY_TYPE_NROOTRDOC = "NROOTRDOC";//numero otro documento relacionado
	private static final String PROPERTY_TYPE_DSCTOTOT = "DSCTOTOT"; //total descuento monto
	private static final String PROPERTY_TYPE_SUMOTRCARG = "SUMOTRCARG";//sumatoria otros cargos
	private static final String PROPERTY_TYPE_SUMOTHTOT = "SUMOTHTOT";//sumatoria OTH total
	private static final String PROPERTY_TYPE_SUMISCTOT = "SUMISCTOT";//sumatoria ISC total
	private static final String PROPERTY_TYPE_SUMIGVTOT = "SUMIGVTOT";//sumatoria IGV total
	private static final String PROPERTY_TYPE_DTOTRDOC = "DTOTRDOC";//fecha otro documento relacionado
	private static final String PROPERTY_TYPE_MOT = "MOT";//Motivo de la nota
	private static final String PROPERTY_TYPE_PUI = "PUI";				//Precio Venta Unitario Item
	private static final String PROPERTY_TYPE_CUI = "CUI";				//Codigo Venta Unitario Item
	java.util.Date date = new java.util.Date();
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/document")
	@Transactional
	public @ResponseBody String saveDocument (@RequestBody DocumentDto documentDto, HttpServletRequest request){
		Document newDocument = convertToDocument(documentDto);
		documentRepository.save(newDocument);
		
		DocumentLog log = new DocumentLog();
		
		log.setDttm(new Timestamp(date.getTime()));
		log.setState("");
		log.setMsg("Documento Recibido");
		
		//Document document_id = documentRepository.findOne(newDocument.getId());
		
		log.setDocument(newDocument);
		documentLogRepository.save(log);
		
		return "OK";
	}

	private Document convertToDocument(DocumentDto documentDto) {
	
	
	
		PropertyType typeTIPOTRDOC = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_TIPOTRDOC);
		PropertyType typeNROGUIAREMI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_NROGUIAREMI);
		PropertyType typeNROOTRDOC= propertyTypeRepository.findByTypeId(PROPERTY_TYPE_NROOTRDOC);
		PropertyType typeDSCTOTOT = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DSCTOTOT);
		PropertyType typeSUMOTRCARG = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SUMOTRCARG);
 		PropertyType typeSUMOTHTOT = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SUMOTHTOT);    		
 		PropertyType typeSUMISCTOT = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SUMISCTOT);
 		PropertyType typeSUMIGVTOT = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SUMIGVTOT);
 		PropertyType typeDTOTRDOC = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_DTOTRDOC);
 		PropertyType typeMOT = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_MOT);
 		PropertyType typePUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PUI);
 		PropertyType typeCUI = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CUI);
 		
 		Customer id_customer = customerRepository.findByIdentification(documentDto.getCustomer());
		Document doc = new Document();
	
	
		
	doc.setLegalNumber(documentDto.getLegalNumber());
	//doc.setIssueDate(Formatting.formatString(documentDto.getIssueDate()));
	doc.setIssueDate(documentDto.getIssueDate());
	doc.setCreatedDate(new Timestamp(date.getTime()));
	if (documentDto.getCurrency().equals("SOLES")) {doc.setCurrency("PEN");}
	else { doc.setCurrency("USD");}
	doc.setCustomer(id_customer);
	
	//doc.setDocumentType(documentTypeRepository.findByTypeId(documentDto.getDocumentTypeCode()));
	doc.setDocumentType(documentTypeRepository.findByTypeId(documentDto.getDocumentTypeCode()));
	doc.setCreatedUser("PORTAL");
	doc.setStatus("RS");
	doc.setAmount(documentDto.getAmount());
	
	DocumentProperty propNROGUIAREMI = new DocumentProperty();
	propNROGUIAREMI.setPropertyType(typeNROGUIAREMI);
	propNROGUIAREMI.setValueString(documentDto.getGuide());
	
	DocumentProperty propDSCTOTOT = new DocumentProperty();
	propDSCTOTOT.setPropertyType(typeDSCTOTOT);
	propDSCTOTOT.setValueString(documentDto.getTotalDiscount());
	
	DocumentProperty propSUMOTRCARG = new DocumentProperty();
	propSUMOTRCARG.setPropertyType(typeSUMOTRCARG);
	propSUMOTRCARG.setValueString(documentDto.getOtherCharges());
	
	//DocumentProperty propSUMOTHTOT = new DocumentProperty();
	//propSUMOTHTOT.setPropertyType(typeSUMOTHTOT);
	//propSUMOTHTOT.setValueString("50.00");
	
	DocumentProperty propSUMISCTOT = new DocumentProperty();
	propSUMISCTOT.setPropertyType(typeSUMISCTOT);
	propSUMISCTOT.setValueString(documentDto.getTaxISC());
	
	DocumentProperty propSUMIGVTOT = new DocumentProperty();
	propSUMIGVTOT.setPropertyType(typeSUMIGVTOT);
	propSUMIGVTOT.setValueString(documentDto.getTaxIGV());
	
	
	
	if (documentDto.getDocumentTypeCode().equals("07") || documentDto.getDocumentTypeCode().equals("08")){
		DocumentProperty propNROOTRDOC = new DocumentProperty();
		propNROOTRDOC.setPropertyType(typeNROOTRDOC);
		propNROOTRDOC.setValueString(documentDto.getNroDocumentRelation());
	
		DocumentProperty propTIPOTRDOC = new DocumentProperty();
		propTIPOTRDOC.setPropertyType(typeTIPOTRDOC);
		propTIPOTRDOC.setValueString(documentDto.getDocumentRelation());
		
		DocumentProperty propDTOTRDOC = new DocumentProperty();
		propDTOTRDOC.setPropertyType(typeDTOTRDOC);
		propDTOTRDOC.setValueString(documentDto.getIssueDateDocumentRelation());
	
		DocumentProperty propMOT = new DocumentProperty();
		propMOT.setPropertyType(typeMOT);
		propMOT.setValueString(documentDto.getReason());
		
		doc.getProperties().add(propNROOTRDOC);
		doc.getProperties().add(propTIPOTRDOC);
		doc.getProperties().add(propDTOTRDOC);
		doc.getProperties().add(propMOT);
		
		propNROOTRDOC.setDocument(doc);
		propTIPOTRDOC.setDocument(doc);
		propDTOTRDOC.setDocument(doc);
		propMOT.setDocument(doc);
		
	}
	
	//recorremos los detalles del documento
	for (int i=0; i < documentDto.getlDetailDocument().size(); i++){
		
		DocumentDetail det = new DocumentDetail();
		//det.setSequence(documentDto.getlDetailDocument().get(i).getItem());
		//System.out.println(documentDto.getlDetailDocument().get(i).getItem());
		det.setCode(documentDto.getlDetailDocument().get(i).getProductCode());
		det.setDescr(documentDto.getlDetailDocument().get(i).getDescription());
		det.setQuantity(new Double(documentDto.getlDetailDocument().get(i).getQuantity()));
		det.setAmount(new Double(documentDto.getlDetailDocument().get(i).getTotal()));
		det.setCreatedDate(new Timestamp(date.getTime()));
		det.setCreatedUser("portal");
		
		
		DocumentDetailProperty propCUI = new DocumentDetailProperty();
		propCUI.setPropertyType(typeCUI);
		propCUI.setValueString(documentDto.getlDetailDocument().get(i).getItem());
		DocumentDetailProperty propPUI = new DocumentDetailProperty();
		propPUI.setPropertyType(typePUI);
		propPUI.setValueAmount(new Double(documentDto.getlDetailDocument().get(i).getPrice()));
		
		det.getProperties().add(propPUI);
		det.getProperties().add(propCUI);
		
		propPUI.setDocumentDetail(det);
		propCUI.setDocumentDetail(det);
		
		doc.getDetails().add(det);
		det.setDocument(doc);
	
		}
	
	
	
	doc.getProperties().add(propSUMOTRCARG);
	doc.getProperties().add(propNROGUIAREMI);
	doc.getProperties().add(propSUMIGVTOT);
	doc.getProperties().add(propSUMISCTOT);
	//doc.getProperties().add(propSUMOTHTOT);
	doc.getProperties().add(propDSCTOTOT);

	propSUMOTRCARG.setDocument(doc);
	propNROGUIAREMI.setDocument(doc);
	propSUMIGVTOT.setDocument(doc);
	propSUMISCTOT.setDocument(doc);
	//propSUMOTHTOT.setDocument(doc);
	propDSCTOTOT.setDocument(doc);
	
	
	return doc;	
	}
	
}
