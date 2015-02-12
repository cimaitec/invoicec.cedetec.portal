package com.cimait.invoicec.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "iv_mapfield")
public class MapField {
	private long id;
	private String documentType;
	private String documentLevel;
	private String source;
	private String target;
	private int length;
	private String dataType;
	private String tableReference;
	private String fieldReference;
	private String country;
	private String sourceType;
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "document_type")
	public String getDocumentType() {
		return documentType;
	}
	
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	@Column(name = "doc_level")
	public String getDocumentLevel() {
		return documentLevel;
	}
	
	public void setDocumentLevel(String documentLevel) {
		this.documentLevel = documentLevel;
	}
	
	@Column(name = "source")
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	@Column(name = "target")
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	@Column(name = "length")
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	@Column(name = "data_type")
	public String getDataType() {
		return dataType;
	}
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Column(name = "table_reference")
	public String getTableReference() {
		return tableReference;
	}
	
	public void setTableReference(String tableReference) {
		this.tableReference = tableReference;
	}
	
	@Column(name = "field_reference")
	public String getFieldReference() {
		return fieldReference;
	}
	
	public void setFieldReference(String fieldReference) {
		this.fieldReference = fieldReference;
	}
	
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name = "source_type")
	public String getSourceType() {
		return sourceType;
	}
	
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((dataType == null) ? 0 : dataType.hashCode());
		result = prime * result
				+ ((documentLevel == null) ? 0 : documentLevel.hashCode());
		result = prime * result
				+ ((documentType == null) ? 0 : documentType.hashCode());
		result = prime * result
				+ ((fieldReference == null) ? 0 : fieldReference.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + length;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result
				+ ((sourceType == null) ? 0 : sourceType.hashCode());
		result = prime * result
				+ ((tableReference == null) ? 0 : tableReference.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapField other = (MapField) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (dataType == null) {
			if (other.dataType != null)
				return false;
		} else if (!dataType.equals(other.dataType))
			return false;
		if (documentLevel == null) {
			if (other.documentLevel != null)
				return false;
		} else if (!documentLevel.equals(other.documentLevel))
			return false;
		if (documentType == null) {
			if (other.documentType != null)
				return false;
		} else if (!documentType.equals(other.documentType))
			return false;
		if (fieldReference == null) {
			if (other.fieldReference != null)
				return false;
		} else if (!fieldReference.equals(other.fieldReference))
			return false;
		if (id != other.id)
			return false;
		if (length != other.length)
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (sourceType == null) {
			if (other.sourceType != null)
				return false;
		} else if (!sourceType.equals(other.sourceType))
			return false;
		if (tableReference == null) {
			if (other.tableReference != null)
				return false;
		} else if (!tableReference.equals(other.tableReference))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}
	
}