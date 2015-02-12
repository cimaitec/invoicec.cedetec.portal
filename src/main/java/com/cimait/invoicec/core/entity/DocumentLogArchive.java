package com.cimait.invoicec.core.entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "iv_doc_log_arch")
public class DocumentLogArchive {
    private long id;
    private long logId;
    private Long docId;
    private Timestamp dttm;
    private String state;
    private String msg;
    private Timestamp archDttm;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "log_id")
    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "doc_id")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    @Basic
    @Column(name = "dttm")
    public Timestamp getDttm() {
        return dttm;
    }

    public void setDttm(Timestamp dttm) {
        this.dttm = dttm;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "msg")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Basic
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

        DocumentLogArchive that = (DocumentLogArchive) o;

        if (id != that.id) return false;
        if (logId != that.logId) return false;
        if (archDttm != null ? !archDttm.equals(that.archDttm) : that.archDttm != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (dttm != null ? !dttm.equals(that.dttm) : that.dttm != null) return false;
        if (msg != null ? !msg.equals(that.msg) : that.msg != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (logId ^ (logId >>> 32));
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (dttm != null ? dttm.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (archDttm != null ? archDttm.hashCode() : 0);
        return result;
    }
}
