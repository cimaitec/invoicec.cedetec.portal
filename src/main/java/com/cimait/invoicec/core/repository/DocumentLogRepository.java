package com.cimait.invoicec.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentLog;

public interface DocumentLogRepository extends CrudRepository<DocumentLog, Long> {
	

}
