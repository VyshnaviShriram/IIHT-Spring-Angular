package com.user.entity;

import java.util.*;

public class UserBookData {
	
	
	private Integer id;
	private Integer userId;
	private Integer bookId;
	private Date subscriptionDate;
	private String subscriptionStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Date getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}
	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}
	public UserBookData() {
		super();
	}
	public UserBookData(Integer userId, Integer bookId, Date subscriptionDate, String subscriptionStatus) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.subscriptionDate = subscriptionDate;
		this.subscriptionStatus = subscriptionStatus;
	}
	
	

}
