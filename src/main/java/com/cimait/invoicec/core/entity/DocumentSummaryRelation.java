package com.cimait.invoicec.core.entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "iv_doc_hdr_summary_rel")
public class DocumentSummaryRelation {
    private long id;
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

        DocumentSummaryRelation that = (DocumentSummaryRelation) o;

        if (id != that.id) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdUser != null ? !createdUser.equals(that.createdUser) : that.createdUser != null) return false;
        if (updatedDate != null ? !updatedDate.equals(that.updatedDate) : that.updatedDate != null) return false;
        if (updatedUser != null ? !updatedUser.equals(that.updatedUser) : that.updatedUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + (updatedUser != null ? updatedUser.hashCode() : 0);
        return result;
    }
}
