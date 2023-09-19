package com.questionnanswer.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

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
	@Transient
	private List<Answers> answers;
	@Transient
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
	public Questions(Integer questionId, String title, String description, Integer userId,
			Date createdDateTime) {
		super();
		this.questionId = questionId;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.createdDateTime = createdDateTime;
	}
	public Questions(Integer questionId, String title, String description, Integer userId, Date createdDateTime,
			List<com.questionnanswer.entity.Answers> answers, String nameofUser) {
		super();
		this.questionId = questionId;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.createdDateTime = createdDateTime;
		this.answers = answers;
		this.nameofUser = nameofUser;
	}
	
	

}
