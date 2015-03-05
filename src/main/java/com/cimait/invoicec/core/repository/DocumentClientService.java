package com.cimait.invoicec.core.repository;

import java.util.List;

import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.portal.core.helpers.DocumentClientFilter;
import com.cimait.invoicec.portal.core.helpers.DocumentFilter;

public interface DocumentClientService {
	
	public List<Document> findDocumentClientByFilter(DocumentClientFilter filter);
	
}
