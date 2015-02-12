package com.cimait.invoicec.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.cimait.invoicec.core.entity.PropertyType;

public interface PropertyTypeRepository extends CrudRepository<PropertyType, Long>{

	public PropertyType findByTypeId(String typeId);

}
