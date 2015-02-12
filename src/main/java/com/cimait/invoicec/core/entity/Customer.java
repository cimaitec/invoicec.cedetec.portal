package com.cimait.invoicec.core.entity;

import javax.persistence.*;
import java.sql.Timestamp;



@Entity
@Table(name = "iv_customer")
public class Customer {
    private long id;
    private String identification;
    private String name;
    private String businessName;
    private String address;
    private String email;
    private Boolean active;
    private String type;
    private Boolean updateClient;
    private Timestamp createdDate;
    private String createdUser;
    private Timestamp updatedDate;
    private String updatedUser;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "identification")
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "business_name")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "update_client")
    public Boolean getUpdateClient() {
        return updateClient;
    }

    public void setUpdateClient(Boolean updateClient) {
        this.updateClient = updateClient;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "created_user")
    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    @Basic
    @Column(name = "updated_date")
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Basic
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

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (active != null ? !active.equals(customer.active) : customer.active != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        if (businessName != null ? !businessName.equals(customer.businessName) : customer.businessName != null)
            return false;
        if (createdDate != null ? !createdDate.equals(customer.createdDate) : customer.createdDate != null)
            return false;
        if (createdUser != null ? !createdUser.equals(customer.createdUser) : customer.createdUser != null)
            return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (identification != null ? !identification.equals(customer.identification) : customer.identification != null)
            return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (type != null ? !type.equals(customer.type) : customer.type != null) return false;
        if (updateClient != null ? !updateClient.equals(customer.updateClient) : customer.updateClient != null)
            return false;
        if (updatedDate != null ? !updatedDate.equals(customer.updatedDate) : customer.updatedDate != null)
            return false;
        if (updatedUser != null ? !updatedUser.equals(customer.updatedUser) : customer.updatedUser != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (identification != null ? identification.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (businessName != null ? businessName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (updateClient != null ? updateClient.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + (updatedUser != null ? updatedUser.hashCode() : 0);
        return result;
    }
}
