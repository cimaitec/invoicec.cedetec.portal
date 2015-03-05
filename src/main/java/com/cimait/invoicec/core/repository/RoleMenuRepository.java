package com.cimait.invoicec.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cimait.invoicec.core.entity.Menu;
import com.cimait.invoicec.core.entity.RoleMenu;
import com.cimait.invoicec.core.entity.RoleMenuPK;

public interface RoleMenuRepository extends CrudRepository<RoleMenu, RoleMenuPK>{
	
	
	public final static String FIND_BY_COD_ROLMENU_QUERY = "SELECT  roleMenu" + 
            " FROM RoleMenu roleMenu" +
            " WHERE roleMenu.codRol = :codRol ";
	
	@Query(FIND_BY_COD_ROLMENU_QUERY)
    public List<RoleMenu> findByCodRoleMenu(@Param("codRol") String codRolMenu);
	
}
