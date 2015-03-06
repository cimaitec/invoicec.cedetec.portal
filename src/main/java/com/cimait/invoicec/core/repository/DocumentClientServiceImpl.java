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
import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.portal.core.helpers.DocumentClientFilter;


@Service
public class DocumentClientServiceImpl implements DocumentClientService{
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Document> findDocumentClientByFilter(DocumentClientFilter filter){
				CriteriaBuilder builder = entityManager.getCriteriaBuilder();
				CriteriaQuery<Document> query = builder.createQuery(Document.class);
				Root<Document> root = query.from(Document.class);
				query.select(root);
				List<Predicate> predicateList = new ArrayList<Predicate>();
				Predicate legalNumberPredicate, docTypeIdPredicate, customerPredicate,emitterPredicate;
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
				
				if(filter.getLegalNumber() != null && !filter.getLegalNumber().equals("") ){
					legalNumberPredicate = builder.equal(root.<Document>get("legalNumber"), filter.getLegalNumber());
					predicateList.add(legalNumberPredicate);
				}
				
				
				if(filter.getCustomerId() != null && !filter.getCustomerId().equals("") ){
							customerPredicate = builder.equal(root.<Customer>get("customer").get("identification"), filter.getCustomerId());
							predicateList.add(customerPredicate);					
				}
				
				if(filter.getEmitterId() != null && !filter.getEmitterId().equals("") ){
					emitterPredicate = builder.equal(root.<Emitter>get("emitter").get("identification"), filter.getEmitterId());
					predicateList.add(emitterPredicate);					
				}
				
				Predicate[] predicates = new Predicate[predicateList.size()];
				query.where(predicateList.toArray(predicates));
			    return entityManager.createQuery(query).getResultList();
	}

	
}
