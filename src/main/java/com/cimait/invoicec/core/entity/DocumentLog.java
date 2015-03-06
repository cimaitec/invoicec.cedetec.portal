package com.cimait.invoicec.core.entity;

import javax.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "iv_doc_log")
public class DocumentLog {
    private long id;
    private Timestamp dttm;
    private String state;
    private String msg;
    private Document doc;

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
		return doc;
	}

	public void setDocument(Document doc) {
		this.doc = doc;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentLog that = (DocumentLog) o;

        if (id != that.id) return false;
        if (dttm != null ? !dttm.equals(that.dttm) : that.dttm != null) return false;
        if (msg != null ? !msg.equals(that.msg) : that.msg != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dttm != null ? dttm.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        return result;
    }
}
