package com.cimait.invoicec.portal.core.dto;

public class DocumentDto {
	
	private String legalNumber; 	
	private String razonSocialComprador;
	private String identificacionComprador;
	private String tipIdentificacionComprador;
	private String codigoDocumento;
	private String issueDate;
	private String currency;
	private String status;
	private Double amount;
	private String active;
	
	
	public String getLegalNumber() {
		return legalNumber;
	}
	public void setLegalNumber(String legalNumber) {
		this.legalNumber = legalNumber;
	}
	public String getRazonSocialComprador() {
		return razonSocialComprador;
	}
	public void setRazonSocialComprador(String razonSocialComprador) {
		this.razonSocialComprador = razonSocialComprador;
	}
	public String getIdentificacionComprador() {
		return identificacionComprador;
	}
	public void setIdentificacionComprador(String identificacionComprador) {
		this.identificacionComprador = identificacionComprador;
	}
	public String getTipIdentificacionComprador() {
		return tipIdentificacionComprador;
	}
	public void setTipIdentificacionComprador(String tipIdentificacionComprador) {
		this.tipIdentificacionComprador = tipIdentificacionComprador;
	}
	public String getCodigoDocumento() {
		return codigoDocumento;
	}
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	
}
