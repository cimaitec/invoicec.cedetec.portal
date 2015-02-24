package com.cimait.invoicec.portal.core.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.entity.EmitterProperty;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.EmitterRepository;
import com.cimait.invoicec.portal.core.dto.EmitterDto;
import com.cimait.invoicec.portal.core.dto.EmitterList;

@Controller
public class EmitterController {

	@Autowired
	protected EmitterRepository emitterRepository;
	
	@Autowired
	protected DocumentRepository documentRepository;
	
	
	private static final String PROPERTY_TYPE_PGEN = "PGEN";
    private static final String PROPERTY_TYPE_PSIG = "PSIG";
    private static final String PROPERTY_TYPE_PAUT = "PAUT";
    private static final String PROPERTY_TYPE_PREJ = "PREJ";
    private static final String PROPERTY_TYPE_PUPL = "PUPL";
    private static final String PROPERTY_TYPE_PTMP = "PTMP";
    private static final String PROPERTY_TYPE_PREC = "PREC";
    private static final String PROPERTY_TYPE_PJAS = "PJAS";
    private static final String PROPERTY_TYPE_PARC = "PARC";
    private static final String PROPERTY_TYPE_SMTPH = "SMTPH";
    private static final String PROPERTY_TYPE_SMTPP = "SMTPP";
    private static final String PROPERTY_TYPE_SMTPU = "SMTPU";
    private static final String PROPERTY_TYPE_SMTPPS = "SMTPPS";
    private static final String PROPERTY_TYPE_EMAILS = "EMAILS";
    private static final String PROPERTY_TYPE_CERPATH= "CERPATH";
    private static final String PROPERTY_TYPE_CERT = "CERT";
    private static final String PROPERTY_TYPE_CERSTU = "CERSTU";
    private static final String PROPERTY_TYPE_CERSTP = "CERSTP";
    
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

	@RequestMapping(method=RequestMethod.GET, value="/api/v1/emitter/list/list")
	@Transactional
	public @ResponseBody List<EmitterList> getAllEmitter() {

		List<EmitterList> lEmitterResult = new ArrayList<EmitterList>();
		for (Emitter emitter :  emitterRepository.findAll()) {
			lEmitterResult.add(mapEmitter(emitter));
		}
		return lEmitterResult;
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/api/v1/emitter")
	public void deleteEmitter(@RequestParam(value="id") String id){
		//solo borrar si no tiene documentos.
		/**Long count = documentRepository.countByRuc(id);
		if (count > 0 ) {
			throw new EmitterInfoException();
		} else {**/
			Emitter tmpEmitter = emitterRepository.findOneByIdentification(id);
			emitterRepository.delete(tmpEmitter);
		//}
	}
	
	

	private EmitterDto mapData(Emitter in) {
		EmitterDto dto = new EmitterDto();
		dto.setActive(in.getActive());
		dto.setAddress(in.getAddress());
		dto.setBusinessName(in.getBusinessName());
		dto.setName(in.getName());
		dto.setIdentification(in.getIdentification());
		dto.setCountryId(in.getCountryId());		
		return dto;
	}
	
	

	private EmitterList mapEmitter(Emitter emitter) {
		EmitterList dto = new EmitterList();
		dto.setAddress(emitter.getAddress());
		dto.setIdentification(emitter.getIdentification());
		dto.setName(emitter.getName());
		dto.setBusinessName(emitter.getBusinessName());
		
		if (!emitter.getActive().equals("true")){
			dto.setActive("Y");
		}else{
			dto.setActive("F");
		}
		
		Iterator i = emitter.getProperties().iterator();
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
			}
		
		}	
		return dto;
		
}
}
