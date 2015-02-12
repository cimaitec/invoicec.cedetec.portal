package com.cimait.invoicec.core.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ea on 10/6/14.
 */
public class UserRolePK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3037849789500016742L;
	private String ruc;
    private String codUsuario;
    private String codRol;

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

    @Column(name = "\"CodRol\"")
    @Id
    public String getCodRol() {
        return codRol;
    }

    public void setCodRol(String codRol) {
        this.codRol = codRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRolePK that = (UserRolePK) o;

        if (codRol != null ? !codRol.equals(that.codRol) : that.codRol != null) return false;
        if (codUsuario != null ? !codUsuario.equals(that.codUsuario) : that.codUsuario != null) return false;
        if (ruc != null ? !ruc.equals(that.ruc) : that.ruc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruc != null ? ruc.hashCode() : 0;
        result = 31 * result + (codUsuario != null ? codUsuario.hashCode() : 0);
        result = 31 * result + (codRol != null ? codRol.hashCode() : 0);
        return result;
    }
}
