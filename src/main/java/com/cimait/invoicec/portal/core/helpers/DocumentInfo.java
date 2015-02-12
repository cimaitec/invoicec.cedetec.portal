package com.cimait.invoicec.portal.core.helpers;

import java.sql.Date;


public class DocumentInfo {
	private Integer ambiente;
	private String ruc;
	private String codEstablecimiento;
	private String codPuntEmision;
	private String secuencial;
	private Date fechaEmision;
	private String identificacionComprador;
	private String tipIdentificacionComprador;
	private String razonSocialComprador;
	private String moneda;
	private Double importeTotal;
	private String codigoDocumento;
	private String isActive;
	private String estadoTransaccion;
	
	public Integer getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(Integer ambiente) {
		this.ambiente = ambiente;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getCodEstablecimiento() {
		return codEstablecimiento;
	}
	public void setCodEstablecimiento(String codEstablecimiento) {
		this.codEstablecimiento = codEstablecimiento;
	}
	public String getCodPuntEmision() {
		return codPuntEmision;
	}
	public void setCodPuntEmision(String codPuntEmision) {
		this.codPuntEmision = codPuntEmision;
	}
	public String getSecuencial() {
		return secuencial;
	}
	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
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
	public String getRazonSocialComprador() {
		return razonSocialComprador;
	}
	public void setRazonSocialComprador(String razonSocialComprador) {
		this.razonSocialComprador = razonSocialComprador;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getCodigoDocumento() {
		return codigoDocumento;
	}
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}
	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}
	
	
}
