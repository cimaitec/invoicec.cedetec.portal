package com.cimait.invoicec.core.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fac_general")
public class General {

private long id;
private String cod_table;
private String value;
private String descripcion;
private Boolean active;


@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

@Column(name = "cod_table")
public String getCod_table() {
	return cod_table;
}

public void setCod_table(String cod_table) {
	this.cod_table = cod_table;
}

@Column(name = "value")
public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

@Column(name = "descripcion")
public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

@Column(name = "active")
public Boolean getActive() {
	return active;
}
public void setActive(Boolean active) {
	this.active = active;
}
	


@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    General general = (General) o;
    
    if (id != general.id) return false;
    if (cod_table != null ? !cod_table.equals(general.cod_table) : general.cod_table != null) return false;
    if (value != null ? !cod_table.equals(general.cod_table) : general.cod_table != null) return false;
    if (descripcion != null ? !cod_table.equals(general.cod_table) : general.cod_table != null) return false;
    if (active != null ? !active.equals(general.active) : general.active != null) return false;
    
    return true;
}

@Override
public int hashCode() {
    
    int result = (int) (id ^ (id >>> 32));
    		
    result = 31 * result + (cod_table != null ? cod_table.hashCode() : 0);
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
    result = 31 * result + (active != null ? active.hashCode() : 0);
    return result;
}

}


