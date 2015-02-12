package com.cimait.invoicec.core.entity;


import javax.persistence.*;

@Entity
@Table(name = "fac_usuarios")
@IdClass(UserPK.class)
public class User {
    private String ruc;
    private String codUsuario;
    private String nombre;
    private String tipoUsuario;
    private String isActive;
    private String password;
    private String rucEmpresa;
    private String email;

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

    @Basic
    @Column(name = "\"Nombre\"")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "\"TipoUsuario\"")
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Basic
    @Column(name = "\"isActive\"")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "\"Password\"")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "\"RucEmpresa\"")
    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }
    
    @Basic
    @Column(name = "\"email\"")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (codUsuario != null ? !codUsuario.equals(user.codUsuario) : user.codUsuario != null) return false;
        if (isActive != null ? !isActive.equals(user.isActive) : user.isActive != null) return false;
        if (nombre != null ? !nombre.equals(user.nombre) : user.nombre != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (ruc != null ? !ruc.equals(user.ruc) : user.ruc != null) return false;
        if (rucEmpresa != null ? !rucEmpresa.equals(user.rucEmpresa) : user.rucEmpresa != null) return false;
        if (tipoUsuario != null ? !tipoUsuario.equals(user.tipoUsuario) : user.tipoUsuario != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruc != null ? ruc.hashCode() : 0;
        result = 31 * result + (codUsuario != null ? codUsuario.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (tipoUsuario != null ? tipoUsuario.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (rucEmpresa != null ? rucEmpresa.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
