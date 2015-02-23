package com.cimait.invoicec.core.repository;

import java.util.Date;
import java.util.List;

import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.portal.core.helpers.DocumentFilter;

public interface DocumentService {
	
	public List<Document> findBeforeDate(String status, Date archivingDate);
	
	public List<Document> findAllByFilter(DocumentFilter filter);
	
}
