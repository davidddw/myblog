package org.tcloud.domain;

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
@Table(name = "mb_articles")
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
		return this.getCategory().getCnName();
	}
	
	@JsonIgnore
	@Transient
	public String[] getCommentsName() {
		Set<Comment> comments = this.comments;
		return (String[]) comments.toArray(new String[comments.size()]);
	}
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name="comment_count", nullable = false, columnDefinition = "int default 0")
	public long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_user")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_category")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name="post_title", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="post_content", nullable = false)
	@Type(type="text")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="post_pv", nullable = false, columnDefinition = "int default 0")
	public long getPv() {
		return pv;
	}
	public void setPv(long pv) {
		this.pv = pv;
	}

	@Column(name="post_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="post_modified", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/**
	 * @return the articleStatus
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="post_status", columnDefinition="enum('PUBLISH','DRAFT','PRIVATE')")
	public ArticleStatusType getArticleStatus() {
		return articleStatus;
	}

	/**
	 * @param articleStatus the articleStatus to set
	 */
	public void setArticleStatus(ArticleStatusType articleStatus) {
		this.articleStatus = articleStatus;
	}

	/**
	 * @return the commentStatus
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="comment_status", columnDefinition="enum('OPEN','CLOSED','REGISTER')")
	public CommentStatusType getCommentStatus() {
		return commentStatus;
	}

	/**
	 * @param commentStatus the commentStatus to set
	 */
	public void setCommentStatus(CommentStatusType commentStatus) {
		this.commentStatus = commentStatus;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "article_tag", joinColumns = { @JoinColumn(name = "article_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY)
	@OrderBy("submitDate asc")
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	/**
	 * @return the tagStrings
	 */
	@Column(name="post_tags")
	public String getTagStrings() {
		return tagStrings;
	}

	/**
	 * @param tagStrings the tagStrings to set
	 */
	public void setTagStrings(String tagStrings) {
		this.tagStrings = tagStrings;
	}

	@Override
	public String toString() {
		return this.title;
	}
}