package com.questionnanswer.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Questions {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer questionId;
	@Column(length = 200, nullable = false)
	private String title;
	@Column(length = 300, nullable = false)
	private String description;
	@Column(nullable = false)
	private Integer userId;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;
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
	public Questions() {
		super();
	}
	public Questions(String title, String description, Integer userId, Date createdDateTime) {
		super();
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.createdDateTime = createdDateTime;
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
