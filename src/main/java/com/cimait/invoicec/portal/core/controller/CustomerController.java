package com.cimait.invoicec.portal.core.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.CustomerProperty;
import com.cimait.invoicec.core.repository.CustomerRepository;
import com.cimait.invoicec.core.repository.EmitterRepository;
import com.cimait.invoicec.portal.core.dto.CustomerDto;



@Controller
public class CustomerController {
	
	@Autowired
	protected CustomerRepository customerRepository;

	@Autowired
	protected EmitterRepository emitterRepository;
	
	private static final String PROPERTY_TYPE_TELEF = "TELEF";
	
	/**
	 * TODO - modificar para que funcione con id de emisor
	 * @return
	 */
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/customer/list")
	@Transactional
	public @ResponseBody List<CustomerDto> getAll(){
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		Iterator i = customers.iterator();
		Customer customer;
		while(i.hasNext()){
					customer =(Customer)i.next();
					customerDtos.add(convertToDto(customer));
		}
		return customerDtos;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/customer")
	@Transactional
	public @ResponseBody String saveCustomer(@RequestBody CustomerDto customerDto, HttpServletRequest request){
			Customer customer = customerRepository.findByIdentification(customerDto.getIdentification());
			if(customer != null){		
						updateCustomer(customer, customerDto);
						customerRepository.save(customer);
			} else {
						Customer newCustomer = convertToCustomer(customerDto);
						customerRepository.save(newCustomer);	
			}
			return "OK";
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/api/v1/customer")
	@Transactional
	public @ResponseBody String deleteCustomer(@RequestParam(value="identification") String identification, @RequestParam(value="identificationEmitter") String identificationEmitter){
		Customer customer = customerRepository.findByIdentification(identification);
		customerRepository.delete(customer.getId());
		return "Ok";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/customer/list/emitter")
	@Transactional
	public @ResponseBody List<CustomerDto> getAll(@RequestParam(value="emitterId",required=false) String emitterId){
		
		System.out.println("llego el id" + emitterId);
		List<Customer> customers = customerRepository.findByEmitterId(emitterId);
		
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		Iterator i = customers.iterator();
		Customer customer;
		while(i.hasNext()){
					customer =(Customer)i.next();
					customerDtos.add(convertToDto(customer));
		}
		return customerDtos;
	}
		


	
	private Customer convertToCustomer(CustomerDto in){
		Customer customer = new Customer();
		customer.setName(in.getName());
		customer.setAddress(in.getAddress());
		customer.setIdentification(in.getIdentification());
		customer.setEmail(in.getEmail());
		customer.setEmitter(emitterRepository.findOneByIdentification(in.getIdentificationEmitter()));
		customer.setType(in.getIdentificationTypeCode().substring(1));
		return customer;
	}
	
	private CustomerDto convertToDto(Customer in) {
		CustomerDto dto = new CustomerDto();
		dto.setId(in.getId());
		dto.setName(in.getName());
		dto.setAddress(in.getAddress());
		dto.setIdentification(in.getIdentification());
		dto.setIdentificationEmitter(in.getEmitter().getIdentification());
		dto.setEmail(in.getEmail());
		dto.setPhone("897897987");		
		Iterator i = in.getProperties().iterator();
		CustomerProperty property;
		while(i.hasNext()){
			property = (CustomerProperty)i.next();
			if (property.getPropertyType().getTypeId().equals(PROPERTY_TYPE_TELEF)) {dto.setPhone(property.getValue());}					
		}
		dto.setIdentificationTypeCode("0" + in.getType()); //TODO se llamara identificationType y guardara el valor de la columna value de fac_general		
		return dto;
	}
	
	private void updateCustomer(Customer dest, CustomerDto in) {
				dest.setAddress(in.getAddress());
				dest.setEmail(in.getEmail());
				dest.setName(in.getName());		
	}
	
}
