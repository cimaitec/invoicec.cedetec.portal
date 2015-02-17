package com.cimait.invoicec.core.repository;



import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cimait.invoicec.core.entity.DocumentType;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	
	
	@Transactional
	public DocumentType findByTypeId(String typeId){
		DocumentType doc = documentTypeRepository.findByTypeId(typeId);
		Hibernate.initialize(doc.getEmitters());
		return doc;		
	}

}
