package org.tcloud.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
 
@Entity
@Table(name = "mb_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GlobalSetting implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5061104505523594100L;
	private long id;
	private String name;
	private String value;
	private String description;
	public GlobalSetting() {}
	
	public GlobalSetting(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="option_name",nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="option_value", nullable = false)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "GlobalSetting:" + name + " " + value;
	}
}