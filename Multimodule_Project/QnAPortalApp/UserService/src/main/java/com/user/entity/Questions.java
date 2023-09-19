package com.user.entity;

import java.util.Date;
import java.util.List;

public class Questions {
	private Integer questionId;
	private String title;
	private String description;
	private Integer userId;
	private Date createdDateTime;
	private List<Answers> answers;
	private String nameofUser;
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<Answers> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}	
	
	public String getNameofUser() {
		return nameofUser;
	}
	public void setNameofUser(String nameofUser) {
		this.nameofUser = nameofUser;
	}
	public Questions() {
		super();
	}
	public Questions(Integer questionId, String title, String description, Integer userId, Date createdDateTime,
			List<Answers> answers, String nameofUser) {
		super();
		this.questionId = questionId;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.createdDateTime = createdDateTime;
		this.answers = answers;
		this.nameofUser = nameofUser;
	}
	public Questions(Integer questionId, String title, String description, Integer userId,
			Date createdDateTime) {
		super();
		this.questionId = questionId;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.createdDateTime = createdDateTime;
	}
	
	
}
