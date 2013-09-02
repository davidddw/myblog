package org.tcloud.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
@Entity
@Table(name = "mb_categories")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3150426234117655652L;
	private long id;
	private String enName;
	private String cnName;
	
	@JsonIgnore
	private Set<Article> articles = new HashSet<Article>();
	
	public Category() {}
	public Category(String enName, String cnName) {
		this.enName = enName;
		this.cnName = cnName;
	}

	@OneToMany(mappedBy = "category")
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="category_en", nullable = false)
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(name="category_cn", nullable = false)
	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String toString() {
		return "Category:" + enName + " " + cnName;
	}
}