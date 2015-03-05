package com.cimait.invoicec.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.Menu;

public interface MenuRepository extends CrudRepository<Menu, String> {
	public final static String FIND_BY_COD_PADRE_QUERY = "SELECT  menu" + 
            " FROM Menu menu" +
            " WHERE menu.isActive in ('1','S')" +
            " and menu.codOpcionMenuPadre = :codOpcionMenuPadre ";
	
	@Query(FIND_BY_COD_PADRE_QUERY)
    public List<Menu> findByCodPadre(@Param("codOpcionMenuPadre") String codOpcionMenuPadre);

}
