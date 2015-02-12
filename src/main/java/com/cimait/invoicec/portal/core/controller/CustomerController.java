package com.cimait.invoicec.portal.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.portal.core.helpers.IdentificationTypeInfo;


@Controller
public class CustomerController {

	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/customer/idTypes")
	public @ResponseBody List<IdentificationTypeInfo> getIdentificationTypes() {
		List <IdentificationTypeInfo> lIDTypes = new ArrayList<IdentificationTypeInfo>();
		IdentificationTypeInfo idType1 = new IdentificationTypeInfo();
		idType1.setCodigo("04");		
		idType1.setDescripcion("RUC");
		
		IdentificationTypeInfo idType2 = new IdentificationTypeInfo();
		idType2.setCodigo("05");		
		idType2.setDescripcion("DOCUMENTO NACIONAL");
		
		IdentificationTypeInfo idType3 = new IdentificationTypeInfo();
		idType3.setCodigo("06");		
		idType3.setDescripcion("PASAPORTE");
		lIDTypes.add(idType1);
		lIDTypes.add(idType2);
		lIDTypes.add(idType3);
		return lIDTypes;
	}
	
	
}
