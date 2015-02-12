package com.cimait.invoicec.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "fac_roles_opcion_menu")
@IdClass(RoleMenuPK.class)
public class RoleMenu {
    private String codRol;
    private String codOpcionMenu;

    @Id
    @Column(name = "\"codRol\"" )
    public String getCodRol() {
        return codRol;
    }

    public void setCodRol(String codRol) {
        this.codRol = codRol;
    }

    @Id
    @Column(name =  "\"CodOpcionMenu\"")
    public String getCodOpcionMenu() {
        return codOpcionMenu;
    }

    public void setCodOpcionMenu(String codOpcionMenu) {
        this.codOpcionMenu = codOpcionMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleMenu roleMenu = (RoleMenu) o;

        if (codOpcionMenu != null ? !codOpcionMenu.equals(roleMenu.codOpcionMenu) : roleMenu.codOpcionMenu != null)
            return false;
        if (codRol != null ? !codRol.equals(roleMenu.codRol) : roleMenu.codRol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codRol != null ? codRol.hashCode() : 0;
        result = 31 * result + (codOpcionMenu != null ? codOpcionMenu.hashCode() : 0);
        return result;
    }
}
