package com.yunshan.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
 
@Entity
@Table(name = "blog_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Options implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5061104505523594100L;
	private long id;
	private String name;
	private String value;
	private String autoload;

	public Options() {}
	
	public Options(String name, String value) {
		this.name = name;
		this.value = value;
		this.autoload = "yes";
	}

	@Id
	@GeneratedValue
	@Column(name = "option_id")
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
	
	@Column(name="autoload", nullable = false)
	public String getAutoload() {
		return autoload;
	}

	public void setAutoload(String autoload) {
		this.autoload = autoload;
	}
	

	public String toString() {
		return "Option:" + name + " " + value;
	}
}