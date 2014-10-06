package com.yunshan.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
 
@Entity
@Table(name = "blog_tag")
public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String intro;
	private long count;
	private Set<Article> articles = new HashSet<Article>();
	
	public Tag() {
	}
	
	public Tag(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@Id
	@GeneratedValue
	@Column(name = "tag_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="tag_name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="tag_intro", nullable = false)
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name="tag_count", nullable = false, columnDefinition = "long default 0")
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	

	@Override 
	public String toString() {
		return name;
	}
	
	@Override  
	public boolean equals(Object obj){
		if(obj == null) return false;  
        if(this == obj) return true;  
        if(obj instanceof Tag)  
            if(name.equals(((Tag)obj).getName()))return true;  
        return false;  
	}	 
		 
	@Override  
	public int hashCode(){ 
		return this.getName().hashCode();
	}

}
