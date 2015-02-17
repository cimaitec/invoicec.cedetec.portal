package com.cimait.invoicec.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cimait.invoicec.core.entity.General;

public interface GeneralRepository extends CrudRepository<General, String> {
	
	public final static String FIND_ID_TYPE = "SELECT gen " + 
            " FROM General gen" +
            " WHERE gen.cod_table = :cod_table";
	
	@Query(FIND_ID_TYPE)
	public List<General> findByCodTabla(@Param("cod_table") String cod_table);

}
