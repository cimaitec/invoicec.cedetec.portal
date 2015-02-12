package com.cimait.invoicec.portal.core.helpers;

import java.math.BigDecimal;
import java.sql.Date;

public class DocumentFilter {

	private int environment;
	private Date beginIssueDate;
	private Date endIssueDate;
	private String customerId;
	private String storeId;
	private String posId;
	private BigDecimal beginSequence;
	private BigDecimal endSequence;
	private String documentType;
	
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
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
	public BigDecimal getBeginSequence() {
		return beginSequence;
	}
	public void setBeginSequence(BigDecimal beginSequence) {
		this.beginSequence = beginSequence;
	}
	public BigDecimal getEndSequence() {
		return endSequence;
	}
	public void setEndSequence(BigDecimal endSequence) {
		this.endSequence = endSequence;
	}
}
