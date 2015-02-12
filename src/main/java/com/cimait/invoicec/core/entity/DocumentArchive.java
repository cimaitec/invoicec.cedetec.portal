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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "iv_doc_hdr_arch")
public class DocumentArchive {
    private long id;
   //private long docId;
    private Long companyCustomerId;
    private String legalNumber;
    private String docTypeId;
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
    private Timestamp archDttm;
    private Collection<DocumentDetailArchive> details = new ArrayList<DocumentDetailArchive>();
	private Collection<DocumentPropertyArchive> properties = new ArrayList<DocumentPropertyArchive>();
    
    
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
    public Collection<DocumentDetailArchive> getDetails() {
		return details;
	}

	public void setDetails(Collection<DocumentDetailArchive> details) {
		this.details = details;
	}

	@OneToMany(mappedBy="document", cascade=CascadeType.ALL)
	public Collection<DocumentPropertyArchive> getProperties() {
		return properties;
	}

	public void setProperties(Collection<DocumentPropertyArchive> properties) {
		this.properties = properties;
	}

    @Column(name = "company_customer_id")
    public Long getCompanyCustomerId() {
        return companyCustomerId;
    }

    public void setCompanyCustomerId(Long companyCustomerId) {
        this.companyCustomerId = companyCustomerId;
    }

    @Column(name = "legal_number")
    public String getLegalNumber() {
        return legalNumber;
    }

    public void setLegalNumber(String legalNumber) {
        this.legalNumber = legalNumber;
    }

    @Column(name = "doc_type_id")
    public String getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(String docTypeId) {
        this.docTypeId = docTypeId;
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

        DocumentArchive that = (DocumentArchive) o;

      
        if (id != that.id) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (archDttm != null ? !archDttm.equals(that.archDttm) : that.archDttm != null) return false;
        if (companyCustomerId != null ? !companyCustomerId.equals(that.companyCustomerId) : that.companyCustomerId != null)
            return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdUser != null ? !createdUser.equals(that.createdUser) : that.createdUser != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (docTypeId != null ? !docTypeId.equals(that.docTypeId) : that.docTypeId != null) return false;
        if (issueDate != null ? !issueDate.equals(that.issueDate) : that.issueDate != null) return false;
        if (legalNumber != null ? !legalNumber.equals(that.legalNumber) : that.legalNumber != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (updatedDate != null ? !updatedDate.equals(that.updatedDate) : that.updatedDate != null) return false;
        if (updatedUser != null ? !updatedUser.equals(that.updatedUser) : that.updatedUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyCustomerId != null ? companyCustomerId.hashCode() : 0);
        result = 31 * result + (legalNumber != null ? legalNumber.hashCode() : 0);
        result = 31 * result + (docTypeId != null ? docTypeId.hashCode() : 0);
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
        result = 31 * result + (archDttm != null ? archDttm.hashCode() : 0);
        return result;
    }
}
