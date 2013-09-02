package org.tcloud.domain;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "mb_tags")
public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String tagValue;
	private Date createdDate;
	private Set<Article> articles = new HashSet<Article>();
	
	public Tag() {
		
	}
	
	public Tag(String tagValue) {
		this.tagValue = tagValue;
		this.createdDate = new Date();
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
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="tag_name", nullable = false)
	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	@Column(name="tag_created", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override 
	public String toString() {
		return tagValue;
	}
	
	@Override  
	public boolean equals(Object obj){
		if(obj == null) return false;  
        if(this == obj) return true;  
        if(obj instanceof Tag)  
            if(tagValue.equals(((Tag)obj).getTagValue()))return true;  
        return false;  
	}	 
		 
	@Override  
	public int hashCode(){ 
		return this.getTagValue().hashCode();
	}

}
