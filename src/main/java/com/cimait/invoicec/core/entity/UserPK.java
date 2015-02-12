package com.cimait.invoicec.core.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserPK implements Serializable { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 6889492424386875513L;
	private String ruc;
    private String codUsuario;

    @Column(name = "\"Ruc\"")
    @Id
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @Column(name = "\"CodUsuario\"")
    @Id
    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPK userPK = (UserPK) o;

        if (codUsuario != null ? !codUsuario.equals(userPK.codUsuario) : userPK.codUsuario != null) return false;
        if (ruc != null ? !ruc.equals(userPK.ruc) : userPK.ruc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruc != null ? ruc.hashCode() : 0;
        result = 31 * result + (codUsuario != null ? codUsuario.hashCode() : 0);
        return result;
    }
}
