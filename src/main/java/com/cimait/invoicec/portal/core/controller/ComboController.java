package com.cimait.invoicec.portal.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.DocumentType;
import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.entity.General;
import com.cimait.invoicec.core.entity.Role;
import com.cimait.invoicec.core.repository.CustomerRepository;
import com.cimait.invoicec.core.repository.DocumentTypeRepository;
import com.cimait.invoicec.core.repository.EmitterRepository;
import com.cimait.invoicec.core.repository.GeneralRepository;
import com.cimait.invoicec.core.repository.RoleRepository;
import com.cimait.invoicec.portal.core.dto.CustomerComboDto;
import com.cimait.invoicec.portal.core.dto.DocumentTypeComboDto;
import com.cimait.invoicec.portal.core.dto.EmitterComboDto;
import com.cimait.invoicec.portal.core.helpers.IdentificationTypeInfo;

@Controller
public class ComboController {


	@Autowired
	protected EmitterRepository emitterRepository;
	
	@Autowired
	protected DocumentTypeRepository documentTypeRepository;
	
	@Autowired
	protected CustomerRepository customerRepository;

	@Autowired
	protected GeneralRepository generalRepository;

	@Autowired
	protected RoleRepository roleRepository;

    @RequestMapping(method=RequestMethod.GET, value="/api/v1/combo/customer/list")
	public @ResponseBody List<CustomerComboDto> getAll(){
				List<Customer> customers = (List<Customer>) customerRepository.findAll();
				List<CustomerComboDto> customerDtos = new ArrayList<CustomerComboDto>();
				for (Customer customer : customers) {
									customerDtos.add(convertToDto(customer));
				}
				return customerDtos;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/combo/emitter/list")
	public @ResponseBody List<EmitterComboDto> emitters() {
		List<Emitter> emitters = (List<Emitter>) emitterRepository.findAll();
		List<EmitterComboDto> emitterDtos = new ArrayList<EmitterComboDto>();
		for (Emitter emitter : emitters) {
							emitterDtos.add(convertToDto(emitter));
		}
		return emitterDtos;		
	}
    

    @RequestMapping(method=RequestMethod.GET, value="/api/v1/combo/documenttype/list")
	public @ResponseBody List<DocumentTypeComboDto> getAllTypes(){		
				List<DocumentType> documentTypes = (List<DocumentType>) documentTypeRepository.findAll();
				List<DocumentTypeComboDto> documentTypeDtos = new ArrayList<DocumentTypeComboDto>();
				for (DocumentType documentType : documentTypes) {
										documentTypeDtos.add(convertToDto(documentType));
				}
				return documentTypeDtos;
    }

	@RequestMapping(method=RequestMethod.GET, value="/api/v1/combo/idtype/list")
	public @ResponseBody List<IdentificationTypeInfo> getIdentificationTypes() {
			List <IdentificationTypeInfo> lIDTypes = new ArrayList<IdentificationTypeInfo>();
			List<General> lGeneral = generalRepository.findByCodTabla("95");			
			for (General general : lGeneral) {
						IdentificationTypeInfo idType = new IdentificationTypeInfo();
						idType.setCodigo(general.getValue());
						idType.setDescripcion(general.getDescripcion());				
						lIDTypes.add(idType);
			}
			return lIDTypes;
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/combo/usertype/list")
	public @ResponseBody List <IdentificationTypeInfo>  getUserTypes() {
		List <IdentificationTypeInfo> listUserType = new ArrayList<IdentificationTypeInfo>();
		List<General> lGeneral = generalRepository.findByCodTabla("1");
		for (General general : lGeneral) {
			IdentificationTypeInfo idType = new IdentificationTypeInfo();
			idType.setCodigo(general.getValue());
			idType.setDescripcion(general.getDescripcion());
			
			listUserType.add(idType);
		}
		return listUserType;
	}		
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/combo/currencytype/list")
	public @ResponseBody List<IdentificationTypeInfo> getCurrencyTypes() {
			List <IdentificationTypeInfo> lCurrencyTypes = new ArrayList<IdentificationTypeInfo>();
			List<General> lGeneral = generalRepository.findByCodTabla("2");		
			for (General general : lGeneral) {
				IdentificationTypeInfo idType = new IdentificationTypeInfo();
				idType.setCodigo(general.getValue());
				idType.setDescripcion(general.getDescripcion());
				
				lCurrencyTypes.add(idType);
			}
			return lCurrencyTypes;
	}		
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/combo/role/list")
	public @ResponseBody List<Role> getRoles() {
			return (List<Role>) roleRepository.findAll();
	}
	
	private CustomerComboDto convertToDto(Customer in) {
				CustomerComboDto dto = new CustomerComboDto();
				dto.setId(in.getId());
				dto.setName(in.getName());
				return dto;
	}
	
	private DocumentTypeComboDto convertToDto(DocumentType in) {
				DocumentTypeComboDto dto = new DocumentTypeComboDto();
				dto.setTypeId(in.getTypeId());
				dto.setDescr(in.getDescr());
				return dto;
	}
	
	private EmitterComboDto convertToDto(Emitter in) {
				EmitterComboDto dto = new EmitterComboDto();
				dto.setIdentification(in.getIdentification());
				dto.setName(in.getName());
				return dto;
	}
	
	
}
