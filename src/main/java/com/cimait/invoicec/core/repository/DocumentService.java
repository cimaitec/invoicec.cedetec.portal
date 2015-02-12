package com.cimait.invoicec.core.repository;

import java.util.Date;
import java.util.List;

import com.cimait.invoicec.core.entity.Document;

public interface DocumentService {
	
	public List<Document> findBeforeDate(String status, Date archivingDate);
	
}
