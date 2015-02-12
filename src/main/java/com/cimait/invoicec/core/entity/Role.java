package com.cimait.invoicec.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "fac_roles")
public class Role {
    private String codRol;
    private String descripcion;
    private String isActive;

    @Id
    @Column(name = "\"CodRol\"")
    public String getCodRol() {
        return codRol;
    }

    public void setCodRol(String codRol) {
        this.codRol = codRol;
    }

    @Basic
    @Column(name = "\"Descripcion\"")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

        Role role = (Role) o;

        if (codRol != null ? !codRol.equals(role.codRol) : role.codRol != null) return false;
        if (descripcion != null ? !descripcion.equals(role.descripcion) : role.descripcion != null) return false;
        if (isActive != null ? !isActive.equals(role.isActive) : role.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codRol != null ? codRol.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
