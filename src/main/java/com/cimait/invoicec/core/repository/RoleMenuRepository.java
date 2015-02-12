package com.cimait.invoicec.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.cimait.invoicec.core.entity.RoleMenu;
import com.cimait.invoicec.core.entity.RoleMenuPK;

public interface RoleMenuRepository extends CrudRepository<RoleMenu, RoleMenuPK>{

}
