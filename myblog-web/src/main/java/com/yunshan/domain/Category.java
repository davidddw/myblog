package com.yunshan.domain;

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
@Table(name = "blog_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3150426234117655652L;
	private long id;
	private String name;
	private String intro;
	private long count;

	@JsonIgnore
	private Set<Article> articles = new HashSet<Article>();
	
	public Category() {}
	public Category(String name, String intro) {
		this.name = name;
		this.intro = intro;
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
	@Column(name = "cate_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="cate_name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="cate_intro", nullable = false)
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	@Column(name="cate_count", nullable = false, columnDefinition = "long default 0")
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	public String toString() {
		return "Category:" + name + " " + intro;
	}
}