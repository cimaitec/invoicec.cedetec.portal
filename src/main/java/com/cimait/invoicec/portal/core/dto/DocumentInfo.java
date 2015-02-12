package com.cimait.invoicec.portal.core.dto;

import java.util.Date;




public class DocumentInfo {
	
	
	
	/**private Integer ambiente;
	private String ruc;
	private String codEstablecimiento;
	private String codPuntEmision;
	private String secuencial;
	**/
	
	private String fechaEmision;
	private String nroDocumento; // codEstablecimiento - codPuntEmision -  secuencial
	private String razonSocialComprador;
	private String moneda;
	private Double importeTotal;
	private String estadoTransaccion;
		
	private String identificacionComprador;
	private String tipIdentificacionComprador;
	private String codigoDocumento;
	private String isActive;

	
	
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
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
