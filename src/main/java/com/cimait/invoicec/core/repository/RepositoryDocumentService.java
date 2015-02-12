package com.cimait.invoicec.core.repository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentDetail;


@Service
public class RepositoryDocumentService implements DocumentService{
	
	//@Autowired
	//private DocumentRepository documentRepository;
	
	
	/**
	 * Recupera los documentos que cumplen con el filtro para ser archivados
	 * Inicializa sus Colecciones Lazy para que puedan ser accedidas desde otros Servicios o Controllers
	 * 
	 */
	@Transactional
	public List<Document> findBeforeDate(String status, Date archivingDate){
		/**	List<Document> docs = documentRepository.findBeforeDate(status, archivingDate);
			Iterator<Document> i = docs.iterator();
			Iterator<DocumentDetail> j = null;
			Document doc = null;
			DocumentDetail detail = null;
			while(i.hasNext()){
						doc = (Document)i.next();
						Hibernate.initialize(doc.getDetails());
						Hibernate.initialize(doc.getProperties());
						j = doc.getDetails().iterator();
						while(j.hasNext()){
								detail=	(DocumentDetail)j.next();
								Hibernate.initialize(detail.getProperties());	
						}
			}
			return docs;**/
		List<Document> docs = null;
		return docs;
		
	}
	
}
