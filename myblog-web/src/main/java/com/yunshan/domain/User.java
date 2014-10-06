package com.yunshan.domain;

import java.io.Serializable;
import java.util.Date;
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
import com.yunshan.utils.StringHelper;
 
@Entity
@Table(name = "blog_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String password;
	private long level;
	private int sex;
	private String email;
	private String qq;
	private String homepage;
	private Date lastvisit;
	private long status;
	private long postarts;
	private long postcomms;
	private String intro;
	private String ip;
	private String agent;

	private Set<Article> articles = new HashSet<Article>();

	public User(){}
	
	public User(String name, String pass){
		this.name = name;
		this.password = StringHelper.encrypt(pass);
		this.lastvisit = new Date();
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
	@Column(name="mem_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="mem_level", nullable=false, columnDefinition = "long default 0")
	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	@Column(name="mem_name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="mem_password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="mem_sex", nullable = false, columnDefinition = "long default 0")
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	
	@Column(name="mem_email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="mem_qq")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Column(name="mem_homepage")
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	@Column(name="mem_lastvisit", nullable = false, 
		columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	public Date getLastvisit() {
		return lastvisit;
	}

	public void setLastvisit(Date lastvisit) {
		this.lastvisit = lastvisit;
	}
	
	@Column(name="mem_status", nullable = false, columnDefinition = "int default 0")
	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}
	
	@Column(name="mem_postarts", nullable = false, columnDefinition = "int default 0")
	public long getPostarts() {
		return postarts;
	}

	public void setPostarts(long postarts) {
		this.postarts = postarts;
	}
	
	@Column(name="mem_postcomms", nullable = false, columnDefinition = "int default 0")
	public long getPostcomms() {
		return postcomms;
	}

	public void setPostcomms(long postcomms) {
		this.postcomms = postcomms;
	}

	@Column(name="mem_intro", nullable = false)
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name="mem_ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name="mem_agent")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
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
