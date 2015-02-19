package com.cimait.invoicec.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.Document;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	public final static String FIND_BY_IDENTIFICATION_QUERY = "SELECT customer " + 
            " FROM Customer customer" +
            " WHERE customer.identification = :identification ";
	
	@Query(FIND_BY_IDENTIFICATION_QUERY)
    public Customer findByIdentification(@Param("identification") String identification);
	
	
	
}
