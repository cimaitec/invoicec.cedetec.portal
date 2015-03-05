package com.cimait.invoicec.portal.core.helpers;

public class DocumentClientFilter {

	private String documentTypeId;
	private Long customerId;
	private Long legalNumber;
	private String emitterId;

	
	public String getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getLegalNumber() {
		return legalNumber;
	}
	public void setLegalNumber(Long legalNumber) {
		this.legalNumber = legalNumber;
	}
	public String getEmitterId() {
		return emitterId;
	}
	public void setEmitterId(String emitterId) {
		this.emitterId = emitterId;
	}
	
	
	
}
