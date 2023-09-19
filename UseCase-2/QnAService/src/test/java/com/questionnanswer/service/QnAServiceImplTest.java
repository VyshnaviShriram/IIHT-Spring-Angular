package com.questionnanswer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.questionnanswer.entity.Answers;
import com.questionnanswer.entity.Questions;
import com.questionnanswer.repository.IAnswersRepository;
import com.questionnanswer.repository.IQuestionsRepository;

@SpringBootTest
public class QnAServiceImplTest {
	
	@MockBean
	private IQuestionsRepository quesRepo;
	
	@MockBean
	private IAnswersRepository ansRepo;
	
	@Autowired
	private IQnAService questionAnswerService;
	
	private List<Questions> questions ;
	private List<Answers> answers;	
	private Questions questionObj1 ;
	private Questions questionObj2 ;
	private Questions questionObj3 ;
	private Questions questionObj4 ;
	private Answers answerObj1;
	private Answers answerObj2;
	private Answers answerObj3;
	private Answers answerObj4;
	private Answers answerObj5;
	private Answers answerObj6;
	private Answers answerObj7;
	
	@BeforeEach
	void setUp() {
		questionObj1 = new Questions(1, "What is a title?", "What is a description?", 1, new Date());
		questionObj2 = new Questions(2, "Abot Java?", "What is a Java?", 2, new Date());
		questionObj3 = new Questions(3, "Abot spring-boot?", "What is a spring-boot?", 1, new Date());
		questionObj4 = new Questions(4, "Abot Eclipse?", "What is a Eclipse?", 3, new Date());
		questions = List.of(questionObj1,questionObj2,questionObj3,questionObj4);
		answerObj1 = new Answers(1, "Java is a programming Language", 2, 2, new Date());
		answerObj2 = new Answers(2, "Its nothing but explanation", 1, 3, new Date());
		answerObj3 = new Answers(3, "Java is a Functional programming Language", 2, 1, new Date());
		answerObj4 = new Answers(4, "Eclipse is a software which helps developers in writing code.", 4, 1, new Date());
		answerObj5 = new Answers(5, "spring-boot is a framework", 3, 2, new Date());
		answerObj6 = new Answers(6, "Eclipse is a software similar to Spring Tool SUite which helps developers in writing code.", 4, 3, new Date());
		answerObj7 = new Answers(7, "Dummy answer", 5, 3, new Date());
		answers = List.of(answerObj1,answerObj2,answerObj3,answerObj4,answerObj5,answerObj6);
		
	}
	
	@Test
	void addQuestionTest() {
		when(quesRepo.save(questionObj1)).thenReturn(questionObj1);
		assertEquals(1, questionAnswerService.addQuestion(questionObj1));
	}
	
	@Test
	void addAnswerTestSuccess() throws Exception {
		when(quesRepo.findByQuestionId(answerObj1.getQuestionId())).thenReturn(List.of(questionObj2));
//		assertEquals(2, quesRepo.findByQuestionId(answerObj1.getQuestionId()));
		when(ansRepo.save(answerObj1)).thenReturn(answerObj1);
		assertEquals(1, questionAnswerService.addAnswer(answerObj1));
	}
	
	@Test
	void addAnswerTestFailure_QuestionNotFound() throws Exception {
		//when(quesRepo.findByQuestionId(answerObj7.getQuestionId())).thenThrow(new Exception("Question not found"));
		assertThrows(Exception.class, ()->{questionAnswerService.addAnswer(answerObj7);});
	}
	
	@Test
	void getAllQuestionsTest() {
		when(quesRepo.findAll()).thenReturn(questions);
		assertEquals(questions, questionAnswerService.getAllQuestions());
	}
	
	@Test
	void getAllQuestionsByIdTest() {
		when(quesRepo.findByUserId(1)).thenReturn(List.of(questionObj1,questionObj3));
		assertEquals(2, questionAnswerService.getAllQuestionsById(1).size());
	}
	
	@Test
	void getAllAnswersByIdTest() {
		when(ansRepo.findByQuestionId(2)).thenReturn(List.of(answerObj1,answerObj3));
		assertEquals(2, questionAnswerService.getAllAnswersById(2).size());
	}
	

}
