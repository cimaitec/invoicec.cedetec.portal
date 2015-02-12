package com.cimait.invoicec.core.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RoleMenuPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2255507488450772942L;
	private String codRol;
    private String codOpcionMenu;

    @Column(name = "\"codRol\"")
    @Id
    public String getCodRol() {
        return codRol;
    }

    public void setCodRol(String codRol) {
        this.codRol = codRol;
    }

    @Column(name = "\"CodOpcionMenu\"")
    @Id
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

        RoleMenuPK that = (RoleMenuPK) o;

        if (codOpcionMenu != null ? !codOpcionMenu.equals(that.codOpcionMenu) : that.codOpcionMenu != null)
            return false;
        if (codRol != null ? !codRol.equals(that.codRol) : that.codRol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codRol != null ? codRol.hashCode() : 0;
        result = 31 * result + (codOpcionMenu != null ? codOpcionMenu.hashCode() : 0);
        return result;
    }
}
