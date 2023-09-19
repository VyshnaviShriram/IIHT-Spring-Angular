package com.user.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Users {
	
	@Id
	@Column(name = "UserId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "FirstName", length =100, nullable = false)
	private String firstName;
	@Column(name = "LastName", length =100, nullable = false)
	private String lastName;
	@Column(name = "EmailId", length =100, nullable = false,unique = true)
	private String emailId;
	@Column(name = "Password", length =100, nullable = false)
	private String password;
	@Column(name = "CreatedDateTime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public Users() {
		super();
	}
	public Users(String firstName, String lastName, String emailId, String password, Date createdDateTime) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.createdDateTime = createdDateTime;
	}
	public Users(Integer userId, String firstName, String lastName, String emailId, String password,
			Date createdDateTime) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.createdDateTime = createdDateTime;
	}
	
	

}
