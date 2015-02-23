package com.cimait.invoicec.portal.core.dto;

public class CustomerDto {

		private Long id;
	   	private String identification;
	    private String name;	    
	    private String address;
	    private String identificationEmitter;
	    private String email;
	    private String phone;
	    private String identificationTypeCode; //RUC, DNI, PASAPORTE
	    
	    
	    public Long getId() {
			return id;
		}
	    public void setId(Long id) {
			this.id = id;
		}				
		public String getIdentification() {
			return identification;
		}
		public void setIdentification(String identification) {
			this.identification = identification;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getIdentificationEmitter() {
			return identificationEmitter;
		}
		public void setIdentificationEmitter(String identificationEmitter) {
			this.identificationEmitter = identificationEmitter;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getIdentificationTypeCode() {
			return identificationTypeCode;
		}
		public void setIdentificationTypeCode(String identificationTypeCode) {
			this.identificationTypeCode = identificationTypeCode;
		}
	    	
}
