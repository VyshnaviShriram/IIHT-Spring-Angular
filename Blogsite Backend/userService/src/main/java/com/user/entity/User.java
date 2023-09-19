package com.user.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")
public class User {
	
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	private Integer userId;
	private String userName;
	private String userEmail;
	private String password;
	private Date createdDate;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User() {
		super();
	}
	public User(Integer userId, String userName, String userEmail, String password, Date createdDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.createdDate = createdDate;
	}
}
