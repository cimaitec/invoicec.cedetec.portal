package com.cimait.invoicec.portal.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.EmitterRepository;
import com.cimait.invoicec.portal.core.dto.EmitterDto;

@Controller
public class EmitterController {

	@Autowired
	protected EmitterRepository emitterRepository;
	
	@Autowired
	protected DocumentRepository documentRepository;
	
	
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
	public @ResponseBody List<Emitter> getAll() {
		return (ArrayList<Emitter>)emitterRepository.findAll();
		/**
		List<EmitterDto> lEmitterResult = new ArrayList<EmitterDto>();
		for (Emitter emitter :  emitterRepository.findAll()) {
			//emitter.setPassFirma("");
			//emitter.setPassSmtp("");
			lEmitterResult.add(mapData(emitter));
		}
		return lEmitterResult;**/
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
	
}
