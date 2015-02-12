package com.cimait.invoicec.core.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "iv_doc_dtl_prop_arch")
public class DocumentDetailPropertyArchive {
    private long id;
    private String valueString;
    private Double valueAmount;
    private Date valueDate;
    private Timestamp archDttm;
    private DocumentDetailArchive documentDetail;
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
	@JoinColumn(name = "dtl_id")
    public DocumentDetailArchive getDocumentDetail() {
		return documentDetail;
	}

	public void setDocumentDetail(DocumentDetailArchive documentDetail) {
		this.documentDetail = documentDetail;
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

    @Column(name = "arch_dttm")
    public Timestamp getArchDttm() {
        return archDttm;
    }

    public void setArchDttm(Timestamp archDttm) {
        this.archDttm = archDttm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentDetailPropertyArchive that = (DocumentDetailPropertyArchive) o;

        if (id != that.id) return false;
        if (archDttm != null ? !archDttm.equals(that.archDttm) : that.archDttm != null) return false;
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
        result = 31 * result + (archDttm != null ? archDttm.hashCode() : 0);
        return result;
    }
}
