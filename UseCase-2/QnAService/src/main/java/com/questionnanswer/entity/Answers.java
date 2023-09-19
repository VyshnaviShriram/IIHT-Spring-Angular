package com.questionnanswer.entity;

import java.util.Date;
import javax.persistence.*;


@Entity
public class Answers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer answerId;
	@Column(length = 300, nullable = false)
	private String answer;
	@Column(nullable = false)
	private Integer questionId;
	@Column(nullable = false)
	private Integer userId;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;
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
	public Answers() {
		super();
	}
	public Answers(Integer answerId, String answer, Integer questionId, Integer userId, Date createdDateTime) {
		super();
		this.answerId = answerId;
		this.answer = answer;
		this.questionId = questionId;
		this.userId = userId;
		this.createdDateTime = createdDateTime;
	}
	
	
	
	

}
