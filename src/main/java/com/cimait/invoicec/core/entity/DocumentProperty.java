package com.cimait.invoicec.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Enrique Almonte on 14/01/2015.
 */
@Entity
@Table(name = "iv_doc_hdr_prop")
public class DocumentProperty {
    private long id;
    private String valueString;
    private Double valueAmount;
    private Date valueDate;
    private Document document;
    private PropertyType propertyType;
    
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	@ManyToOne
	@JoinColumn(name = "doc_id")  
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
    
	@OneToOne
	@JoinColumn(name = "property_type_id")
	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
   
    @Column(name = "value_string")
    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    
    @Column(name = "value_amount")
    public Double getValueAmount() {
        return valueAmount;
    }

    public void setValueAmount(Double valueAmount) {
        this.valueAmount = valueAmount;
    }

    
    @Column(name = "value_date")
    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }
        
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentProperty that = (DocumentProperty) o;

        if (id != that.id) return false;
        if (valueAmount != null ? !valueAmount.equals(that.valueAmount) : that.valueAmount != null) return false;
        if (valueDate != null ? !valueDate.equals(that.valueDate) : that.valueDate != null) return false;
        if (valueString != null ? !valueString.equals(that.valueString) : that.valueString != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (valueString != null ? valueString.hashCode() : 0);
        result = 31 * result + (valueAmount != null ? valueAmount.hashCode() : 0);
        result = 31 * result + (valueDate != null ? valueDate.hashCode() : 0);
        return result;
    }
}
