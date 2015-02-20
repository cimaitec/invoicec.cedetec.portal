package com.cimait.invoicec.core.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cimait.invoicec.core.entity.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long>{

	public final static String FIND_BEFORE_DATE_QUERY = "SELECT doc " + 
            " FROM Document doc" +
            " WHERE doc.status = :status AND " + 
            " doc.issueDate < :archivingDate";
	
	public final static String FIND_ALL_BY_FILTER = "SELECT doc " + 
            " FROM Document doc" + 
			" WHERE "; 
	
            //" WHERE doc.legal_number = :status AND " + 
            //" doc.issueDate < :archivingDate";		
	//"where d.ruc=?1 and d.ambiente=?2 and d.identificacionComprador like ?3 and d.fechaEmision between ?4 and ?5 and to_number(d.secuencial,'999999999') between ?6 and ?7 and codigoDocumento like ?8 order by d.fechaEmision desc"
	
	
	
	/**
	@Query("select d from Document d "
			"where d.ruc=?1 and d.ambiente='0' and 
			d.identificacionComprador like ?2 and 
			d.fechaEmision between ?3 and ?4 and 
			to_number(d.secuencial,'999999999') between ?5 and ?6 
			and codigoDocumento like ?7 and 
			estadoTransaccion = 'AT' 
			order by d.fechaEmision desc")	
	**/
	
	
	
	
	
	
	
	
	
	
	
	
	@Query(FIND_BEFORE_DATE_QUERY)
    public List<Document> findBeforeDate(@Param("status") String status, @Param("archivingDate") Date archivingDate);
	
	
	
	@Query(FIND_ALL_BY_FILTER)
	public List<Document> findAllByFilter(String ruc, int ambiente, String identificacionComprador, Date beginDate, Date endDate, BigDecimal beginSequence , BigDecimal endSequence, String documentType);

	
}
