package com.cimait.invoicec.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.cimait.invoicec.core.entity.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
}
