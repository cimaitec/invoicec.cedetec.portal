package com.cimait.invoicec.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cimait.invoicec.core.entity.MapField;

public interface MapFieldRepository extends CrudRepository<MapField, Long>{

	public final static String FIND_BY_TIPO_NIVEL_ORIGEN_QUERY = "SELECT map " + 
														            " FROM MapField map" +
														            " WHERE map.documentType = :tipoDocumento AND " + 
														            " map.documentLevel = :nivelDocumento AND " + 
														            " map.sourceType = :origen";

	@Query(FIND_BY_TIPO_NIVEL_ORIGEN_QUERY)
    public List<MapField> find(@Param("tipoDocumento") String tipoDocumento, @Param("nivelDocumento") String nivelDocumento, @Param("origen") String origen);
   
	
	
        
    
    
}
