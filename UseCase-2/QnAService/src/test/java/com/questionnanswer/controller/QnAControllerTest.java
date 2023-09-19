package com.questionnanswer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.questionnanswer.entity.Answers;
import com.questionnanswer.entity.Questions;
import com.questionnanswer.repository.IAnswersRepository;
import com.questionnanswer.repository.IQuestionsRepository;
import com.questionnanswer.service.IQnAService;

@ExtendWith(MockitoExtension.class)
public class QnAControllerTest {
		
	@InjectMocks
	QnAController qNaController;
	
	@Mock
	private IQnAService qNaService;
	
	@Mock
	private IQuestionsRepository quesRepo;
	
	@Mock
	private IAnswersRepository ansRepo;
	
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
		answerObj1 = new Answers(1, "Java is a programming Language", 2, 2, new Date());
		answerObj2 = new Answers(2, "Its nothing but explanation", 1, 3, new Date());
		answerObj3 = new Answers(3, "Java is a Functional programming Language", 3, 1, new Date());
		answerObj4 = new Answers(4, "Eclipse is a software which helps developers in writing code.", 4, 1, new Date());
		answerObj5 = new Answers(5, "spring-boot is a framework", 2, 2, new Date());
		answerObj6 = new Answers(6, "Eclipse is a software similar to Spring Tool SUite which helps developers in writing code.", 4, 3, new Date());
		answerObj7 = new Answers(7, "Dummy answer", 5, 3, new Date());
		
	}
	
	@Test
	void PostNewQuestionTest() throws Exception {		
		when(qNaService.addQuestion(questionObj1)).thenReturn(questionObj1.getQuestionId());
		assertEquals(1, qNaController.PostNewQuestion(questionObj1));
	}
	
	@Test
	void PostNewAnswerTest() throws Exception {
		when(qNaService.addAnswer(answerObj1)).thenReturn(answerObj1.getAnswerId());
		assertEquals(1, qNaController.PostNewAnswer(answerObj1));		
	}
	
	@Test
	void retrieveAllQuestionsByIdTest() {
		when(qNaService.getAllQuestionsById(1)).thenReturn(List.of(questionObj1,questionObj3));
		assertEquals(2, qNaController.retrieveAllQuestionsById(Optional.of(1)).size());
	}
	
	@Test
	void retrieveAllQuestionsByIdTest_withoutUserId() {
		when(qNaService.getAllQuestions()).thenReturn(List.of(questionObj1,questionObj2,questionObj3,questionObj4));
		assertEquals(4, qNaController.retrieveAllQuestionsById(Optional.empty()).size());
	}
	
	@Test
	void retrieveAllAnswersByIdTest() {
		when(qNaService.getAllAnswersById(2)).thenReturn(List.of(answerObj1,answerObj5));
		assertEquals(2, qNaController.retrieveAllAnswersById(2).size());
	}
}

