package com.cimait.invoicec.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "iv_company_prop")
public class EmitterProperty {
    private long id;
    private String value;
    private Emitter emitter;
    private PropertyType propertyType; 
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
	@JoinColumn(name = "company_id")
	public Emitter getEmitter() {
		return emitter;
	}

	public void setEmitter(Emitter emitter) {
		this.emitter = emitter;
	}

	@OneToOne
	@JoinColumn(name = "property_type_id")
	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
    
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmitterProperty that = (EmitterProperty) o;
        if (id != that.id) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
