package com.user.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Answers {
	
	private Integer answerId;
	private String answer;
	private Integer questionId;
	private Integer userId;
	private Date createdDateTime;
	private String nameofUser;
	
	public Integer getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	public String getNameofUser() {
		return nameofUser;
	}
	public void setNameofUser(String nameofUser) {
		this.nameofUser = nameofUser;
	}
	public Answers() {
		super();
	}
	public Answers(String answer, Integer questionId, Integer userId, Date createdDateTime, String nameofUser) {
		super();
		this.answer = answer;
		this.questionId = questionId;
		this.userId = userId;
		this.createdDateTime = createdDateTime;
		this.nameofUser = nameofUser;
	}
	
	

}
