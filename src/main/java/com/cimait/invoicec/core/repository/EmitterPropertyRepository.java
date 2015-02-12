package com.cimait.invoicec.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cimait.invoicec.core.entity.EmitterProperty;

public interface EmitterPropertyRepository extends CrudRepository<EmitterProperty, Long>{

	public static final String PROPERTY_TYPE_CERTIFICATE_TYPE = "CERT"; //puede ser un keystore o un certificado solo
	public static final String PROPERTY_TYPE_PATH_AUTHORIZED = "PAUT";
	public static final String PROPERTY_TYPE_PATH_GENERATED = "PGEN";
	public static final String PROPERTY_TYPE_PATH_RECEIVED = "PREC";
	public static final String PROPERTY_TYPE_PATH_ARCHIVED = "PARC";
	
	
	
	
	public final static String FIND_BY_CODE_QUERY = "SELECT prop " + 
            " FROM EmitterProperty prop" +
            " WHERE prop.emitter.id = :companyId AND " + 
            " prop.propertyType.typeId = :propertyType";
	
    
    @Query(FIND_BY_CODE_QUERY)
    public EmitterProperty findByCode(@Param("companyId") Long companyId, @Param("propertyType") String propertyType);
   
    
}
