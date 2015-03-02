package com.cimait.invoicec.portal.core.dto;

public class DocumentDetailDto {
	
private String item;
private String productCode;
private String description;
private Double quantity;
private Double price;
private Double taxIGVUnit;
private Double total;


public String getItem() {
	return item;
}
public void setItem(String item) {
	this.item = item;
}
public String getProductCode() {
	return productCode;
}
public void setProductCode(String productCode) {
	this.productCode = productCode;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Double getQuantity() {
	return quantity;
}
public void setQuantity(Double quantity) {
	this.quantity = quantity;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public Double getTaxIGVUnit() {
	return taxIGVUnit;
}
public void setTaxIGVUnit(Double taxIGVUnit) {
	this.taxIGVUnit = taxIGVUnit;
}
public Double getTotal() {
	return total;
}
public void setTotal(Double total) {
	this.total = total;
}


	
	

}
