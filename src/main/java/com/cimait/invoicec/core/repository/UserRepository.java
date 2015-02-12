package com.cimait.invoicec.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cimait.invoicec.core.entity.User;
import com.cimait.invoicec.core.entity.UserPK;

public interface  UserRepository extends CrudRepository<User, UserPK>{

	public User findByRucAndCodUsuarioAndTipoUsuario(String Ruc, String Codusuario, String TipoUsuario);
	
	public List<User> findAllByRuc(String Ruc);
}
