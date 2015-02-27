package com.cimait.invoicec.portal.core.dto;

import java.util.Date;

public class DocumentDto {
	
	private String legalNumber;
	private String customerName;
	private String documentTypeCode;
	private Date issueDate;
	private String currency;
	private String status;
	private Double amount;
	private String company;
	private String customer;
	private String customerIdentification;
	private String customerAddress;
	private String customerEmail;
	private String customerPhone;
	private String guide;
	private String taxIGV;
	private String taxISC;
	private String otherCharges;
	private String totalDiscount;
	
	
	
	public String getLegalNumber() {
		return legalNumber;
	}
	public void setLegalNumber(String legalNumber) {
		this.legalNumber = legalNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}	
	public String getDocumentTypeCode() {
		return documentTypeCode;
	}
	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCustomerIdentification() {
		return customerIdentification;
	}
	public void setCustomerIdentification(String customerIdentification) {
		this.customerIdentification = customerIdentification;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getGuide() {
		return guide;
	}
	public void setGuide(String guide) {
		this.guide = guide;
	}
	
	public String getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}
	public String getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(String totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public String getTaxIGV() {
		return taxIGV;
	}
	public void setTaxIGV(String taxIGV) {
		this.taxIGV = taxIGV;
	}
	public String getTaxISC() {
		return taxISC;
	}
	public void setTaxISC(String taxISC) {
		this.taxISC = taxISC;
	}
	
	
	
}
