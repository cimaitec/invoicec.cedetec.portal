package com.cimait.invoicec.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.cimait.invoicec.core.entity.DocumentType;

public interface DocumentTypeRepository extends CrudRepository<DocumentType, Long>{

	public DocumentType findByTypeId(String typeId);

}
