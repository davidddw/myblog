package org.tcloud.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.tcloud.support.StringHelper;
 
@Entity
@Table(name = "mb_users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String nickname;
	private String email;
	private String url;
	private Date registeredDate;
	private String password;
	private StatusType userStatus;

	private Set<Article> articles = new HashSet<Article>();
	public enum StatusType {
        ENABLE, DISABLE
	}

	public User(){}
	
	public User(String name, String pass){
		this.name = name;
		this.nickname = name;
		this.password = StringHelper.encrypt(pass);
		this.registeredDate = new Date();
		this.userStatus = StatusType.DISABLE;
	}
	
	@OneToMany(mappedBy = "user")
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
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

	@Column(name="user_name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the nickname
	 */
	@Column(name="user_nickname")
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the email
	 */
	@Column(name="user_email")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the url
	 */
	@Column(name="user_url")
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the registeredDate
	 */
	
	@Column(name="user_registered", nullable = false)
	public Date getRegisteredDate() {
		return registeredDate;
	}

	/**
	 * @param registeredDate the registeredDate to set
	 */
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	/**
	 * @return the userStatus
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="user_status", columnDefinition="enum('ENABLE','DISABLE')")
	public StatusType getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus the userStatus to set
	 */
	public void setUserStatus(StatusType userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name="user_pass", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override 
	public String toString() {
		return name;
	}
	
	@Override  
	public boolean equals(Object obj){
		if(obj == null) return false;  
        if(this == obj) return true;  
        if(obj instanceof User)  
            if(name.equals(((User)obj).getName()))return true;  
        return false;  
	}	 
		 
	@Override  
	public int hashCode(){ 
		return this.getName().hashCode();
	}
}
