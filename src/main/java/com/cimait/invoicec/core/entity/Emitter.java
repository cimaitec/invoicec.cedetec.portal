package com.cimait.invoicec.core.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "iv_company")
public class Emitter {

	private long id;
    private String identification;
    private String name;
    private String businessName;    
    private String address;
    private Long schedId;
    private String countryId;
    private Boolean archive;
    private Integer archiveTime;    
    private String archiveTypeTime;
    private Boolean active;
    private Boolean certificateInDb;
    private byte[] certificateFile;
    private Timestamp createdDate;
    private String createdUser;
    private Timestamp updatedDate;
    private String updatedUser;
	private Collection<EmitterProperty> properties = new ArrayList<EmitterProperty>();
	private Collection<DocumentType> documentTypes = new ArrayList<DocumentType>();
	
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @OneToMany(mappedBy="emitter", cascade=CascadeType.ALL)
    public Collection<EmitterProperty> getProperties() {
		return properties;
	}

	public void setProperties(Collection<EmitterProperty> properties) {
		this.properties = properties;
	}

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="iv_company_doc_type", joinColumns=@JoinColumn(name="company_id"),
	inverseJoinColumns=@JoinColumn(name="doc_type_id"))
	public Collection<DocumentType> getDocumentTypes() {
		return documentTypes;
	}

	public void setDocumentTypes(Collection<DocumentType> documentTypes) {
		this.documentTypes = documentTypes;
	}	
	
    @Column(name = "identification")
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
    
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "business_name")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name = "sched_id")
    public Long getSchedId() {
        return schedId;
    }

    public void setSchedId(Long schedId) {
        this.schedId = schedId;
    }

    @Column(name = "country_id")
    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

   @Column(name = "archive")
    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }
    
    @Column(name = "archive_time")
    public Integer getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Integer archiveTime) {
        this.archiveTime = archiveTime;
    }
        
    @Column(name = "archive_type_time")
    public String getArchiveTypeTime() {
        return archiveTypeTime;
    }

    public void setArchiveTypeTime(String archiveTypeTime) {
        this.archiveTypeTime = archiveTypeTime;
    }
    
    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    @Column(name = "certificate_in_db")
    public Boolean getCertificateInDb() {
        return certificateInDb;
    }

    public void setCertificateInDb(Boolean certificateInDb) {
        this.certificateInDb = certificateInDb;
    }

    @Column(name = "certificate_file")
    public byte[] getCertificateFile() {
        return certificateFile;
    }

    public void setCertificateFile(byte[] certificateFile) {
        this.certificateFile = certificateFile;
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

        Emitter emitter = (Emitter) o;

        if (id != emitter.id) return false;
        if (active != null ? !active.equals(emitter.active) : emitter.active != null) return false;
        if (address != null ? !address.equals(emitter.address) : emitter.address != null) return false;
        if (archive != null ? !archive.equals(emitter.archive) : emitter.archive != null) return false;
        if (archiveTime != null ? !archiveTime.equals(emitter.archiveTime) : emitter.archiveTime != null) return false;
        if (archiveTypeTime != null ? !archiveTypeTime.equals(emitter.archiveTypeTime) : emitter.archiveTypeTime != null)
            return false;
        if (businessName != null ? !businessName.equals(emitter.businessName) : emitter.businessName != null)
            return false;
        if (!Arrays.equals(certificateFile, emitter.certificateFile)) return false;
        if (certificateInDb != null ? !certificateInDb.equals(emitter.certificateInDb) : emitter.certificateInDb != null)
            return false;
        if (countryId != null ? !countryId.equals(emitter.countryId) : emitter.countryId != null) return false;
        if (createdDate != null ? !createdDate.equals(emitter.createdDate) : emitter.createdDate != null) return false;
        if (createdUser != null ? !createdUser.equals(emitter.createdUser) : emitter.createdUser != null) return false;
        if (identification != null ? !identification.equals(emitter.identification) : emitter.identification != null)
            return false;
        if (name != null ? !name.equals(emitter.name) : emitter.name != null) return false;
        if (schedId != null ? !schedId.equals(emitter.schedId) : emitter.schedId != null) return false;
        if (updatedDate != null ? !updatedDate.equals(emitter.updatedDate) : emitter.updatedDate != null) return false;
        if (updatedUser != null ? !updatedUser.equals(emitter.updatedUser) : emitter.updatedUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (identification != null ? identification.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (businessName != null ? businessName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (schedId != null ? schedId.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (archive != null ? archive.hashCode() : 0);
        result = 31 * result + (archiveTime != null ? archiveTime.hashCode() : 0);
        result = 31 * result + (archiveTypeTime != null ? archiveTypeTime.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (certificateInDb != null ? certificateInDb.hashCode() : 0);
        result = 31 * result + (certificateFile != null ? Arrays.hashCode(certificateFile) : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + (updatedUser != null ? updatedUser.hashCode() : 0);
        return result;
    }
}