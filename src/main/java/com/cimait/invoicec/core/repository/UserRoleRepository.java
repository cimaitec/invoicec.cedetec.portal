package com.cimait.invoicec.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.cimait.invoicec.core.entity.UserRole;
import com.cimait.invoicec.core.entity.UserRolePK;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRolePK>{

	public UserRole findByRucAndCodUsuarioAndIsActive(String ruc, String codigo, String active);
	
	public UserRole findByRucAndCodUsuario(String ruc, String codigo);
	
}
