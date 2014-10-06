package com.yunshan.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
@Entity
@Table(name = "blog_article")
public class Article implements Serializable{
	/**
	 * @Transient
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	
	@JsonIgnore
	private User user;
	
	@JsonIgnore
	private Category category;
	private String title;
	private String content;
	private long pv;
	private Date createdDate;
	private Date modifiedDate;
	private String excerpt;
	private String name;
	private long commentCount;
	private ArticleStatusType articleStatus;
	private CommentStatusType commentStatus;
	private String tagStrings;
	
	@JsonIgnore
	private Set<Comment> comments = new HashSet<Comment>();
	@JsonIgnore
	private Set<Tag> tags = new HashSet<Tag>();
	
	public enum ArticleStatusType {
        PUBLISH, DRAFT, PRIVATE
	}
	public enum CommentStatusType {
        OPEN, CLOSED, REGISTER
	}
	
	public Article() {}
	public Article(String title ,String content){
		this.title = title;
		this.content = content;
		this.commentCount = 0;
		this.createdDate = new Date();
		this.articleStatus = ArticleStatusType.DRAFT;
		this.commentStatus = CommentStatusType.OPEN;
	}
	
	@Transient
	public String getUserName() {
		return this.getUser().getName();
	}
	
	@Transient
	public String getCategoryName() {
		return this.getCategory().getIntro();
	}
	
	@JsonIgnore
	@Transient
	public String[] getCommentsName() {
		Set<Comment> comments = this.comments;
		return (String[]) comments.toArray(new String[comments.size()]);
	}
	
	@Id
	@GeneratedValue
	@Column(name = "art_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="art_author")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="art_category")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Column(name="art_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="art_content", nullable = false)
	@Type(type="text")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="art_title", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="art_excerpt")
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="art_status", nullable = false, 
		columnDefinition="enum('PUBLISH','DRAFT','PRIVATE') default 'PUBLISH'")
	public ArticleStatusType getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(ArticleStatusType articleStatus) {
		this.articleStatus = articleStatus;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="comment_status", nullable = false, 
		columnDefinition="enum('OPEN','CLOSED','REGISTER') default 'OPEN'")
	public CommentStatusType getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(CommentStatusType commentStatus) {
		this.commentStatus = commentStatus;
	}
	
	@Column(name="art_name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="art_modified", nullable = false, 
		columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name="comment_count", nullable = false, columnDefinition = "int default 0")
	public long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	@Column(name="art_viewnum", nullable = false, columnDefinition = "int default 0")
	public long getPv() {
		return pv;
	}
	public void setPv(long pv) {
		this.pv = pv;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "blog_article_tag", joinColumns = { @JoinColumn(name = "art_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY)
	@OrderBy("comm_posttime asc")
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@Column(name="art_tagstring")
	public String getTagStrings() {
		return tagStrings;
	}

	public void setTagStrings(String tagStrings) {
		this.tagStrings = tagStrings;
	}

	@Override
	public String toString() {
		return this.title;
	}
}