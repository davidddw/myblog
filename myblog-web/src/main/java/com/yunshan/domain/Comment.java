package com.yunshan.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

 
@Entity
@Table(name = "blog_comment")
public class Comment implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 652812670523069696L;
	private long id;
	private long authorid;
	private String author;
	private String content;
	private String email;
	private String homepage;
	private Date posttime;
	private String ip;
	private String agent;
	
	@JsonIgnore
	private Article article;
	
	public Comment() {}
	
	public Comment(String author, String email, String content) {
		this.author = author;
		this.email = email;
		this.content = content;
		this.posttime = new Date();
	}

	@Id
	@GeneratedValue
	@Column(name = "comm_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="comm_authorid", nullable=false, columnDefinition="default 0")
	public long getAuthorid() {
		return authorid;
	}

	public void setAuthorid(long authorid) {
		this.authorid = authorid;
	}

	@Column(name="comm_author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
	@Column(name="comm_email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="comm_content", nullable = false)
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="comm_homepage", nullable = false)
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	@Column(name="comm_posttime", nullable = false,
		columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	@Column(name="comm_ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name="comm_agent")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="art_id")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return content;
	}
}
