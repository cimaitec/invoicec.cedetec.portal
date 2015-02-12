package com.cimait.invoicec.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "fac_opcion_menu")
public class Menu {
    private String codOpcionMenu;
    private String descripcion;
    private String urlPages;
    private String paramDefault;
    private String isActive;
    private String codOpcionMenuPadre;

    @Id
    @Column(name = "\"CodOpcionMenu\"")
    public String getCodOpcionMenu() {
        return codOpcionMenu;
    }

    public void setCodOpcionMenu(String codOpcionMenu) {
        this.codOpcionMenu = codOpcionMenu;
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
    @Column(name = "\"UrlPages\"")
    public String getUrlPages() {
        return urlPages;
    }

    public void setUrlPages(String urlPages) {
        this.urlPages = urlPages;
    }

    @Basic
    @Column(name = "\"ParamDefault\"")
    public String getParamDefault() {
        return paramDefault;
    }

    public void setParamDefault(String paramDefault) {
        this.paramDefault = paramDefault;
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
    @Column(name = "\"codOpcionMenuPadre\"")
    public String getCodOpcionMenuPadre() {
        return codOpcionMenuPadre;
    }

    public void setCodOpcionMenuPadre(String codOpcionMenuPadre) {
        this.codOpcionMenuPadre = codOpcionMenuPadre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (codOpcionMenu != null ? !codOpcionMenu.equals(menu.codOpcionMenu) : menu.codOpcionMenu != null)
            return false;
        if (codOpcionMenuPadre != null ? !codOpcionMenuPadre.equals(menu.codOpcionMenuPadre) : menu.codOpcionMenuPadre != null)
            return false;
        if (descripcion != null ? !descripcion.equals(menu.descripcion) : menu.descripcion != null) return false;
        if (isActive != null ? !isActive.equals(menu.isActive) : menu.isActive != null) return false;
        if (paramDefault != null ? !paramDefault.equals(menu.paramDefault) : menu.paramDefault != null) return false;
        if (urlPages != null ? !urlPages.equals(menu.urlPages) : menu.urlPages != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codOpcionMenu != null ? codOpcionMenu.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (urlPages != null ? urlPages.hashCode() : 0);
        result = 31 * result + (paramDefault != null ? paramDefault.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (codOpcionMenuPadre != null ? codOpcionMenuPadre.hashCode() : 0);
        return result;
    }
}
