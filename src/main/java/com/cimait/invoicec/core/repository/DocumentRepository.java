package com.cimait.invoicec.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cimait.invoicec.core.entity.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long>{

	public final static String FIND_BEFORE_DATE_QUERY = "SELECT doc " + 
            " FROM Document doc" +
            " WHERE doc.status = :status AND " + 
            " doc.issueDate < :archivingDate";

	@Query(FIND_BEFORE_DATE_QUERY)
    public List<Document> findBeforeDate(@Param("status") String status, @Param("archivingDate") Date archivingDate);
	
	
	
}
