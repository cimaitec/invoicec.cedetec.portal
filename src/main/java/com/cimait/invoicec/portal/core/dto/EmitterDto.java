package com.cimait.invoicec.portal.core.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class EmitterDto {

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
	
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }    
    
    public Long getSchedId() {
        return schedId;
    }

    public void setSchedId(Long schedId) {
        this.schedId = schedId;
    }
    
    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
   
    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }    
    
    public Integer getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Integer archiveTime) {
        this.archiveTime = archiveTime;
    }        
    
    public String getArchiveTypeTime() {
        return archiveTypeTime;
    }

    public void setArchiveTypeTime(String archiveTypeTime) {
        this.archiveTypeTime = archiveTypeTime;
    }    
    
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }    
    
    public Boolean getCertificateInDb() {
        return certificateInDb;
    }

    public void setCertificateInDb(Boolean certificateInDb) {
        this.certificateInDb = certificateInDb;
    }
    
    public byte[] getCertificateFile() {
        return certificateFile;
    }

    public void setCertificateFile(byte[] certificateFile) {
        this.certificateFile = certificateFile;
    }    
    
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }    
    
    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }    
    
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }
}