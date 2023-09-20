package com.user.entity;

import java.util.Date;

public class Blog {

	private Integer blogId;
	private String blogName;
	private String authorName;
	private Integer userId;
	private String category;
	private String article;
	private Date createdTime;
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Blog() {
		super();
	}
	public Blog(Integer blogId, String blogName, String authorName, Integer userId, String category, String article,
			Date createdTime) {
		super();
		this.blogId = blogId;
		this.blogName = blogName;
		this.authorName = authorName;
		this.userId = userId;
		this.category = category;
		this.article = article;
		this.createdTime = createdTime;
	}
	
}
