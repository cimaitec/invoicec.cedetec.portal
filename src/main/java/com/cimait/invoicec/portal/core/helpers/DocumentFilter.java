package com.cimait.invoicec.portal.core.helpers;

import java.math.BigDecimal;
import java.sql.Date;

public class DocumentFilter {

	private int environment;
	private Date beginIssueDate;
	private Date endIssueDate;
	private Long customerId;
	private String storeId;
	private String posId;
	private Long beginSequence;
	private Long endSequence;
	private String documentTypeId;
	
	public String getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public int getEnvironment() {
		return environment;
	}
	public void setEnvironment(int envionment) {
		this.environment = envionment;
	}
	public Date getBeginIssueDate() {
		return beginIssueDate;
	}
	public void setBeginIssueDate(Date beginIssueDate) {
		this.beginIssueDate = beginIssueDate;
	}
	public Date getEndIssueDate() {
		return endIssueDate;
	}
	public void setEndIssueDate(Date endIssueDate) {
		this.endIssueDate = endIssueDate;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public Long getBeginSequence() {
		return beginSequence;
	}
	public void setBeginSequence(Long beginSequence) {
		this.beginSequence = beginSequence;
	}
	public Long getEndSequence() {
		return endSequence;
	}
	public void setEndSequence(Long endSequence) {
		this.endSequence = endSequence;
	}
}
