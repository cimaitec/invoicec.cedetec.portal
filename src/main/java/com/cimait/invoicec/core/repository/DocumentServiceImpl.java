package com.cimait.invoicec.core.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentDetail;
import com.cimait.invoicec.core.entity.DocumentType;
import com.cimait.invoicec.portal.core.helpers.DocumentFilter;


@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	/**
	 * Recupera los documentos que cumplen con el filtro para ser archivados
	 * Inicializa sus Colecciones Lazy para que puedan ser accedidas desde otros Servicios o Controllers
	 * 
	 */
	@Transactional
	public List<Document> findBeforeDate(String status, Date archivingDate){
			List<Document> docs = documentRepository.findBeforeDate(status, archivingDate);
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
			return docs;
	}
	
	public List<Document> findAllByFilter(DocumentFilter filter){
				CriteriaBuilder builder = entityManager.getCriteriaBuilder();
				CriteriaQuery<Document> query = builder.createQuery(Document.class);
				Root<Document> root = query.from(Document.class);
				query.select(root);
				List<Predicate> predicateList = new ArrayList<Predicate>();
				Predicate legalNumberPredicate, docTypeIdPredicate, issueDatePredicate, customerPredicate;
				/**
				if(true){
							legalNumberPredicate = builder.like(builder.upper(root.<String>get("legalNumber")), "%"+"F002-00000001"+"%");
					        predicateList.add(legalNumberPredicate);
				}
				if(filter.getBeginSequence() != null && filter.getEndSequence() != null){
					issueDatePredicate = builder.between(getSequence(root.<Date>get("legalNumber")), filter.getBeginSequence(), filter.getEndSequence());
					predicateList.add(issueDatePredicate);
				}
				 **/
				
				
				if(filter.getDocumentTypeId() != null && !filter.getDocumentTypeId().equals("") ){
							docTypeIdPredicate = builder.equal(root.<DocumentType>get("documentType").get("typeId"), filter.getDocumentTypeId());
							predicateList.add(docTypeIdPredicate);
				}
				if(filter.getBeginIssueDate() != null && filter.getEndIssueDate() != null){
							issueDatePredicate = builder.between(root.<Date>get("issueDate"), filter.getBeginIssueDate(), filter.getEndIssueDate());
							predicateList.add(issueDatePredicate);
				}
				if(filter.getCustomerId() != null ){
							customerPredicate = builder.equal(root.<Customer>get("customer").get("id"), filter.getCustomerId());
							predicateList.add(customerPredicate);					
				}
				Predicate[] predicates = new Predicate[predicateList.size()];
				query.where(predicateList.toArray(predicates));
			    return entityManager.createQuery(query).getResultList();
	}
	
}
