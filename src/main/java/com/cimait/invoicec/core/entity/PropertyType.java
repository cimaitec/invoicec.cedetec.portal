package com.cimait.invoicec.core.entity;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "iv_property_type")
public class PropertyType {
    private long id;
    private String typeId;
    private String code;
    private String descr;
    private Boolean encrypt;
    private Boolean mandatory;
    private String country;
    private Timestamp createdDate;
    private String createdUser;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "type_id")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
    
    @Column(name = "encrypt")
    public Boolean getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Boolean encrypt) {
        this.encrypt = encrypt;
    }

    @Column(name = "mandatory")
    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyType that = (PropertyType) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdUser != null ? !createdUser.equals(that.createdUser) : that.createdUser != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (encrypt != null ? !encrypt.equals(that.encrypt) : that.encrypt != null) return false;
        if (mandatory != null ? !mandatory.equals(that.mandatory) : that.mandatory != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (encrypt != null ? encrypt.hashCode() : 0);
        result = 31 * result + (mandatory != null ? mandatory.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        return result;
    }
}
