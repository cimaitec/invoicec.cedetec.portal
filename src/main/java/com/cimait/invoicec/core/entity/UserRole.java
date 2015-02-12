package com.cimait.invoicec.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "fac_usuarios_roles")
@IdClass(UserRolePK.class)
public class UserRole {
    private String ruc;
    private String codUsuario;
    private String codRol;
    private String isActive;

    @Id
    @Column(name = "\"Ruc\"")
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @Id
    @Column(name = "\"CodUsuario\"")
    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Id
    @Column(name = "\"CodRol\"")
    public String getCodRol() {
        return codRol;
    }

    public void setCodRol(String codRol) {
        this.codRol = codRol;
    }

    @Basic
    @Column(name = "\"isActive\"")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (codRol != null ? !codRol.equals(userRole.codRol) : userRole.codRol != null) return false;
        if (codUsuario != null ? !codUsuario.equals(userRole.codUsuario) : userRole.codUsuario != null) return false;
        if (isActive != null ? !isActive.equals(userRole.isActive) : userRole.isActive != null) return false;
        if (ruc != null ? !ruc.equals(userRole.ruc) : userRole.ruc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruc != null ? ruc.hashCode() : 0;
        result = 31 * result + (codUsuario != null ? codUsuario.hashCode() : 0);
        result = 31 * result + (codRol != null ? codRol.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
