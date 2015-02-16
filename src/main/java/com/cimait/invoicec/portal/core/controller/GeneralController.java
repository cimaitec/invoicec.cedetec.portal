package com.cimait.invoicec.portal.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.General;
import com.cimait.invoicec.core.repository.GeneralRepository;
import com.cimait.invoicec.portal.core.helpers.IdentificationTypeInfo;




@Controller
public class GeneralController {

	@Autowired
	protected GeneralRepository generalRepository;

		
		@RequestMapping(method=RequestMethod.GET, value="/api/v1/customer/idTypes")
		public @ResponseBody List<IdentificationTypeInfo> getIdentificationTypes() {
			List <IdentificationTypeInfo> lIDTypes = new ArrayList<IdentificationTypeInfo>();
			List<General> lGeneral = generalRepository.findByCodTabla("95");
			//List<General> lGeneral = (List<General>)generalRepository.findAll();
			for (General general : lGeneral) {
				IdentificationTypeInfo idType = new IdentificationTypeInfo();
				idType.setCodigo(general.getValue());
				idType.setDescripcion(general.getDescripcion());
				
				lIDTypes.add(idType);
			}
			return lIDTypes;
		}	
		
		
		@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/currencyTypes")
		public @ResponseBody List<IdentificationTypeInfo> getCurrencyTypes() {
			List <IdentificationTypeInfo> lCurrencyTypes = new ArrayList<IdentificationTypeInfo>();
			List<General> lGeneral = generalRepository.findByCodTabla("2");
			//List<General> lGeneral = (List<General>)generalRepository.findAll();
			for (General general : lGeneral) {
				IdentificationTypeInfo idType = new IdentificationTypeInfo();
				idType.setCodigo(general.getValue());
				idType.setDescripcion(general.getDescripcion());
				
				lCurrencyTypes.add(idType);
			}
			return lCurrencyTypes;
		}		
		
	
		@RequestMapping(method=RequestMethod.GET, value="/api/v1/user/types")
		public @ResponseBody List <IdentificationTypeInfo>  getUserTypes() {
			List <IdentificationTypeInfo> listUserType = new ArrayList<IdentificationTypeInfo>();
			List<General> lGeneral = generalRepository.findByCodTabla("1");
			//List<General> lGeneral = (List<General>)generalRepository.findAll();
			for (General general : lGeneral) {
				IdentificationTypeInfo idType = new IdentificationTypeInfo();
				idType.setCodigo(general.getValue());
				idType.setDescripcion(general.getDescripcion());
				
				listUserType.add(idType);
			}
			return listUserType;

		}	
	}
	


