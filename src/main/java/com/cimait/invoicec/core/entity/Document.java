package com.cimait.invoicec.core.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "iv_doc_hdr")
public class Document implements Cloneable{
    private long id;
    private String legalNumber;
    private Date issueDate;
    private String currency;
    private Double amount;
    private Boolean active;
    private String status;
    private String source;
    private Timestamp createdDate;
    private String createdUser;
    private Timestamp updatedDate;
    private String updatedUser;
	private Collection<DocumentDetail> details = new ArrayList<DocumentDetail>();
	private Collection<DocumentProperty> properties = new ArrayList<DocumentProperty>();
	private Customer customer;
	private DocumentType documentType;
	 //private Long docTypeId;
	 
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy="document", cascade=CascadeType.ALL)
	public Collection<DocumentDetail> getDetails() {
		return details;
	}

	public void setDetails(Collection<DocumentDetail> details) {
		this.details = details;
	}    

	@OneToMany(mappedBy="document", cascade=CascadeType.ALL)
	public Collection<DocumentProperty> getProperties() {
		return properties;
	}

	public void setProperties(Collection<DocumentProperty> properties) {
		this.properties = properties;
	}	
	
	@ManyToOne
	@JoinColumn(name = "customer_id")  
    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToOne
	@JoinColumn(name="doc_type_id")
	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}	
	
	@Column(name = "legal_number")
    public String getLegalNumber() {
        return legalNumber;
    }

    public void setLegalNumber(String legalNumber) {
        this.legalNumber = legalNumber;
    }
    
        
    @Column(name = "issue_date")
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    
    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

        Document document = (Document) o;

        if (id != document.id) return false;
        if (active != null ? !active.equals(document.active) : document.active != null) return false;
        if (amount != null ? !amount.equals(document.amount) : document.amount != null) return false;
        if (createdDate != null ? !createdDate.equals(document.createdDate) : document.createdDate != null)
            return false;
        if (createdUser != null ? !createdUser.equals(document.createdUser) : document.createdUser != null)
            return false;
        if (currency != null ? !currency.equals(document.currency) : document.currency != null) return false;
          if (issueDate != null ? !issueDate.equals(document.issueDate) : document.issueDate != null) return false;
        if (legalNumber != null ? !legalNumber.equals(document.legalNumber) : document.legalNumber != null)
            return false;
        if (source != null ? !source.equals(document.source) : document.source != null) return false;
        if (status != null ? !status.equals(document.status) : document.status != null) return false;
        if (updatedDate != null ? !updatedDate.equals(document.updatedDate) : document.updatedDate != null)
            return false;
        if (updatedUser != null ? !updatedUser.equals(document.updatedUser) : document.updatedUser != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (legalNumber != null ? legalNumber.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + (updatedUser != null ? updatedUser.hashCode() : 0);
        return result;
    }

}
