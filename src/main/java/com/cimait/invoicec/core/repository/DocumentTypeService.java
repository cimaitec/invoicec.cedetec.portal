package com.cimait.invoicec.core.repository;

import com.cimait.invoicec.core.entity.DocumentType;

public interface DocumentTypeService {
	
	public DocumentType findByTypeId(String typeId);

}
