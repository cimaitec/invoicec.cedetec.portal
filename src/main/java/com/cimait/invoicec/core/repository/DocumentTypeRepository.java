package com.cimait.invoicec.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cimait.invoicec.core.entity.DocumentType;


public interface DocumentTypeRepository extends CrudRepository<DocumentType, Long>{

	
	public final static String FIND_BY_TYPEID = "SELECT doc " + 
            " FROM DocumentType doc" +
            " WHERE doc.typeId = :typeId ";
		
	
	@Query(FIND_BY_TYPEID)	
	public DocumentType findByTypeId(@Param("typeId") String typeId);

}
