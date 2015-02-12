package com.cimait.invoicec.core.entity;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "iv_doc_dtl")
public class DocumentDetail {
    private long id;
    private String sequence;
    private String code;
    private String descr;
    private Double quantity;
    private Double amount;
    private Timestamp createdDate;
    private String createdUser;
    private Timestamp updatedDate;
    private String updatedUser;
	private Document document;	
	private Collection<DocumentDetailProperty> properties = new ArrayList<DocumentDetailProperty>();
    
    
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
    
	@OneToMany(mappedBy="documentDetail", cascade=CascadeType.ALL)
    public Collection<DocumentDetailProperty> getProperties() {
		return properties;
	}

	public void setProperties(Collection<DocumentDetailProperty> properties) {
		this.properties = properties;
	}
   
    @Column(name = "sequence")
    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name = "descr")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    @Column(name = "quantity")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    
    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    @Column(name = "created_user")
    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    
    @Column(name = "updated_date")
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    @Column(name = "updated_user")
    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentDetail that = (DocumentDetail) o;

        if (id != that.id) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdUser != null ? !createdUser.equals(that.createdUser) : that.createdUser != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (sequence != null ? !sequence.equals(that.sequence) : that.sequence != null) return false;
        if (updatedDate != null ? !updatedDate.equals(that.updatedDate) : that.updatedDate != null) return false;
        if (updatedUser != null ? !updatedUser.equals(that.updatedUser) : that.updatedUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (sequence != null ? sequence.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + (updatedUser != null ? updatedUser.hashCode() : 0);
        return result;
    }
}
