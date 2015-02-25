package com.cimait.invoicec.portal.core.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.entity.EmitterProperty;
import com.cimait.invoicec.core.entity.PropertyType;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.EmitterRepository;
import com.cimait.invoicec.core.repository.PropertyTypeRepository;
import com.cimait.invoicec.portal.core.dto.EmitterDto;

@Controller
public class EmitterController {

	@Autowired
	protected EmitterRepository emitterRepository;
	
	@Autowired
	protected DocumentRepository documentRepository;
	
	
	@Autowired
	protected PropertyTypeRepository propertyTypeRepository;
	
	
	private static final String PROPERTY_TYPE_PGEN = "PGEN";
    private static final String PROPERTY_TYPE_PSIG = "PSIG";
    private static final String PROPERTY_TYPE_PAUT = "PAUT";
    private static final String PROPERTY_TYPE_PREJ = "PREJ";
    private static final String PROPERTY_TYPE_PREC = "PREC";
    private static final String PROPERTY_TYPE_PJAS = "PJAS";
    private static final String PROPERTY_TYPE_SMTPH = "SMTPH";
    private static final String PROPERTY_TYPE_SMTPP = "SMTPP";
    private static final String PROPERTY_TYPE_SMTPU = "SMTPU";
    private static final String PROPERTY_TYPE_SMTPPS = "SMTPPS";
    private static final String PROPERTY_TYPE_EMAILS = "EMAILS";
    private static final String PROPERTY_TYPE_CERPATH= "CERPATH";
    private static final String PROPERTY_TYPE_CERT = "CERT";
    private static final String PROPERTY_TYPE_CERSTU = "CERSTU";
    private static final String PROPERTY_TYPE_CERSTP = "CERSTP";
    private static final String PROPERTY_TYPE_SMTPS = "SMTPS";
    private static final String PROPERTY_TYPE_EMAILR = "EMAILR";
    private static final String PROPERTY_TYPE_EMAILRU = "EMAILRU";
    private static final String PROPERTY_TYPE_EMAILRP = "EMAILRP";
    
    
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/emitter")
	public @ResponseBody Emitter get(@RequestParam(value="id") String id){
		Emitter tmpEmitter = emitterRepository.findOneByIdentification(id);
		if (tmpEmitter == null) {
			throw new EntityNotFoundException();
		} else {
			//remuevo datos no necesarios
			//tmpEmitter.setPassFirma("");
			//tmpEmitter.setPassSmtp("");
		}
		return tmpEmitter;
	}

	@RequestMapping(method=RequestMethod.GET, value="/api/v1/emitter/list")
	@Transactional
	public @ResponseBody List<EmitterDto> getAllEmitter() {

		List<EmitterDto> lEmitterResult = new ArrayList<EmitterDto>();
		for (Emitter emitter :  emitterRepository.findAll()) {
			lEmitterResult.add(mapData(emitter));
		}
		return lEmitterResult;
	}
	
	

	@RequestMapping(method=RequestMethod.POST, value="/api/v1/emitter")
	@Transactional
	public @ResponseBody String saveEmitter(@RequestBody EmitterDto emitterDto, HttpServletRequest request){
			System.out.println("llego" + emitterDto.getIdentification());
			Emitter emitter = emitterRepository.findOneByIdentification(emitterDto.getIdentification());
			if(emitter != null){		
						updateCustomer(emitter, emitterDto);
						emitterRepository.save(emitter);
			} else {
						Emitter newEmitter = convertToEmitter(emitterDto);
								emitterRepository.save(newEmitter);	
			}
			return "OK";
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/api/v1/emitter")
	@Transactional
	public @ResponseBody String deleteCustomer(@RequestParam(value="identification") String identification){
		Emitter emitter = emitterRepository.findOneByIdentification(identification);
		emitterRepository.delete(emitter.getId());
		return "Ok";
	}
	
	private EmitterDto mapData(Emitter in) {
		EmitterDto dto = new EmitterDto();
		dto.setAddress(in.getAddress());
		dto.setIdentification(in.getIdentification());
		dto.setName(in.getName());
		dto.setBusinessName(in.getBusinessName());
		
		if (in.getActive() == true){
			dto.setActive("Y");
		}else{
			dto.setActive("F");
		}
		
		Iterator i = in.getProperties().iterator();
		EmitterProperty property;
		while(i.hasNext()){
			property = (EmitterProperty)i.next();
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PGEN)){
				dto.setPathGen(property.getValue());
			}else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PSIG)){
				dto.setPathSig(property.getValue());
			}else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PAUT)){
				dto.setPathAut(property.getValue());
			}else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PREC)){
				dto.setPatchRec(property.getValue());
			}else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PREJ)){
				dto.setPathRej(property.getValue());
			}else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PJAS)){
				dto.setPathJas(property.getValue());
			}else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPH)){
				dto.setSmtpServer(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPP)){
				dto.setSmtpPort(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPU)){
				dto.setSmtpUser(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPPS)){
				dto.setSmtpPassword(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_EMAILS)){
				dto.setSmtpMail(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_CERPATH)){
				dto.setPathCertificate(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_CERT)){
				dto.setCertificateType(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_CERSTU)){
				dto.setCertificateAlias(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_CERSTP)){
				dto.setCertificatePassword(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPS)){
				dto.setFlagSmtp(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_EMAILR)){
				dto.setReceiveMail(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_EMAILRU)){
				dto.setUserReceiveMail(property.getValue());
			} else if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_EMAILRP)){
				dto.setPassReceiveMail(property.getValue());
			}
		
		}	
		return dto;	
	}

	
	private Emitter convertToEmitter(EmitterDto in){
		Emitter emitter = new Emitter();
		boolean isActive = true;
		boolean notActive = false;
		emitter.setIdentification(in.getIdentification());
		emitter.setName(in.getName());
		emitter.setBusinessName(in.getBusinessName());
		emitter.setAddress(in.getAddress());
			if (in.getActive().equals("Y")){
				emitter.setActive(isActive);
				} else {
				emitter.setActive(notActive);
				}
		emitter.setCountryId("PE");
		emitter.setArchive(true);
		emitter.setArchiveTime(1);
		emitter.setArchiveTypeTime("Y");
		emitter.setCertificateInDb(true);
		emitter.setCertificateFile(null);
		emitter.setCreatedDate(null);
		emitter.setCreatedUser("INSTALATION");
		java.util.Date date = new java.util.Date();
		emitter.setCreatedDate(new Timestamp(date.getTime()));
		
	
		PropertyType typePGEN = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PGEN);
		PropertyType typePSIG = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PSIG);
		PropertyType typePAUT = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PAUT);
		PropertyType typePREJ = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PREJ);
		PropertyType typePREC = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PREC);
		PropertyType typePJAS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_PJAS);
		PropertyType typeCERT = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERT);  
		PropertyType typeSMTPH = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SMTPH);
		PropertyType typeSMTPP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SMTPP);
		PropertyType typeSMTPU = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SMTPU);
		PropertyType typeSMTPPS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SMTPPS);
		PropertyType typeEMAILS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILS);
		PropertyType typeCERPATH = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERPATH);
		PropertyType typeCERSTU = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERSTU);
		PropertyType typeCERSTP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_CERSTP);
		PropertyType typeSMTPS = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_SMTPS);
		PropertyType typeEMAILR = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILR);
		PropertyType typeEMAILRU = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILRU);
		PropertyType typeEMAILRP = propertyTypeRepository.findByTypeId(PROPERTY_TYPE_EMAILRP);
  	
   		EmitterProperty propPGEN = new EmitterProperty();
		propPGEN.setPropertyType(typePGEN);
		propPGEN.setValue(in.getPathGen());
		
		
		EmitterProperty propPSIG = new EmitterProperty();
		propPSIG.setPropertyType(typePSIG);
		propPSIG.setValue(in.getPathSig());
		
		EmitterProperty propPAUT = new EmitterProperty();
		propPAUT.setPropertyType(typePAUT);
		propPAUT.setValue(in.getPathAut());
		
		EmitterProperty propPREJ = new EmitterProperty();
		propPREJ.setPropertyType(typePREJ);
		propPREJ.setValue(in.getPathRej());
		
		EmitterProperty propPJAS = new EmitterProperty();
		propPJAS.setPropertyType(typePJAS);
		propPJAS.setValue(in.getPathJas());
		
		EmitterProperty propPREC = new EmitterProperty();
		propPREC.setPropertyType(typePREC);
		propPREC.setValue(in.getPatchRec());
		
		EmitterProperty propSMTPH = new EmitterProperty();
		propSMTPH.setPropertyType(typeSMTPH);
		propSMTPH.setValue(in.getSmtpServer());
	
		EmitterProperty propSMTPP = new EmitterProperty();
		propSMTPP.setPropertyType(typeSMTPP);
		propSMTPP.setValue(in.getSmtpPort());
		
		EmitterProperty propSMTPU = new EmitterProperty();
		propSMTPU.setPropertyType(typeSMTPU);
		propSMTPU.setValue(in.getSmtpUser());
		
		EmitterProperty propSMTPPS = new EmitterProperty();
		propSMTPPS.setPropertyType(typeSMTPPS);
		propSMTPPS.setValue(in.getSmtpPassword());
		
		EmitterProperty propEMAILS = new EmitterProperty();
		propEMAILS.setPropertyType(typeEMAILS);
		propEMAILS.setValue(in.getSmtpMail());
		
		EmitterProperty propCERPATH = new EmitterProperty();
		propCERPATH.setPropertyType(typeCERPATH);
		propCERPATH.setValue(in.getPathCertificate());
		
		EmitterProperty propCERSTU = new EmitterProperty();
		propCERSTU.setPropertyType(typeCERSTU);
		propCERSTU.setValue(in.getCertificateAlias());
		
		EmitterProperty propCERSTP = new EmitterProperty();
		propCERSTP.setPropertyType(typeCERSTP);
		propCERSTP.setValue(in.getCertificatePassword());
		
		EmitterProperty propCERT = new EmitterProperty();
		propCERT.setPropertyType(typeCERT);
		propCERT.setValue(in.getCertificateType());
		
		EmitterProperty propSMTPS = new EmitterProperty();
		propSMTPS.setPropertyType(typeSMTPS);
		propSMTPS.setValue(in.getFlagSmtp());
		
		EmitterProperty propEMAILR = new EmitterProperty();
		propEMAILR.setPropertyType(typeEMAILR);
		propEMAILR.setValue(in.getReceiveMail());
		
		EmitterProperty propEMAILRU = new EmitterProperty();
		propEMAILRU.setPropertyType(typeEMAILRU);
		propEMAILRU.setValue(in.getUserReceiveMail());
		
		EmitterProperty propEMAILRP = new EmitterProperty();
		propEMAILRP.setPropertyType(typeEMAILRP);
		propEMAILRP.setValue(in.getPassReceiveMail());
		
		
		emitter.getProperties().add(propPGEN);
		emitter.getProperties().add(propPSIG);
		emitter.getProperties().add(propPAUT);
		emitter.getProperties().add(propPJAS);
		emitter.getProperties().add(propSMTPH);
		emitter.getProperties().add(propSMTPP);
		emitter.getProperties().add(propSMTPU);
		emitter.getProperties().add(propSMTPPS);
		emitter.getProperties().add(propPREJ);
		emitter.getProperties().add(propPREC);
		emitter.getProperties().add(propEMAILS);
		emitter.getProperties().add(propCERPATH);
		emitter.getProperties().add(propCERSTU);
		emitter.getProperties().add(propCERSTP);
		emitter.getProperties().add(propCERT);
		emitter.getProperties().add(propSMTPS);
		emitter.getProperties().add(propEMAILR);
		emitter.getProperties().add(propEMAILRU);
		emitter.getProperties().add(propEMAILRP);
		
		propPGEN.setEmitter(emitter);
		propPSIG.setEmitter(emitter);
		propPAUT.setEmitter(emitter);
		propPJAS.setEmitter(emitter);
		propSMTPH.setEmitter(emitter);
		propSMTPP.setEmitter(emitter);
		propSMTPU.setEmitter(emitter);
		propSMTPPS.setEmitter(emitter);
		propPREJ.setEmitter(emitter);
		propPREC.setEmitter(emitter);
		propEMAILS.setEmitter(emitter);
		propCERPATH.setEmitter(emitter);
		propCERSTU.setEmitter(emitter);
		propCERSTP.setEmitter(emitter);
		propCERT.setEmitter(emitter);
		propSMTPS.setEmitter(emitter);
		propEMAILR.setEmitter(emitter);
		propEMAILRU.setEmitter(emitter);
		propEMAILRP.setEmitter(emitter);
		
		return emitter;
	}
	
	private void updateCustomer(Emitter dest, EmitterDto in) {
		boolean isActive = true;
		boolean notActive = false;
		dest.setAddress(in.getAddress());
		if (in.getActive().equals("Y")){
		dest.setActive(isActive);	
		}else {
		dest.setActive(notActive);
		}
		java.util.Date date = new java.util.Date();
		dest.setUpdatedDate(new Timestamp(date.getTime()));
		Iterator i = dest.getProperties().iterator();
		EmitterProperty property;
		while(i.hasNext()){
			property = (EmitterProperty)i.next();
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PSIG)) {property.setValue(in.getPathSig());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PGEN)) {property.setValue(in.getPathGen());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PAUT)) {property.setValue(in.getPathAut());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PREJ)) {property.setValue(in.getPathRej());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PREC)) {property.setValue(in.getPatchRec());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_PJAS)) {property.setValue(in.getPathJas());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPH)) {property.setValue(in.getSmtpServer());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPP)) {property.setValue(in.getSmtpPort());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPU)) {property.setValue(in.getSmtpUser());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPPS)) {property.setValue(in.getSmtpPassword());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_EMAILS)) {property.setValue(in.getSmtpMail());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_CERPATH)) {property.setValue(in.getPathCertificate());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_CERT)) {property.setValue(in.getCertificateType());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_CERSTU)) {property.setValue(in.getCertificateAlias());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_CERSTP)) {property.setValue(in.getCertificatePassword());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_SMTPS)) {property.setValue(in.getFlagSmtp());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_EMAILR)) {property.setValue(in.getReceiveMail());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_EMAILRU)) {property.setValue(in.getUserReceiveMail());}
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_EMAILRP)) {property.setValue(in.getPassReceiveMail());}
			}
		}
}

	
	
	

