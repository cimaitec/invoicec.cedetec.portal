package com.cimait.invoicec.core.repository;

import com.cimait.invoicec.core.entity.Emitter;
import org.springframework.data.repository.CrudRepository;

public interface EmitterRepository extends CrudRepository<Emitter, Long>{

	public Emitter findOneByIdentification(String identification);
	
}
