package com.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.entity.Answers;
import com.user.entity.JwtRequest;
import com.user.entity.Questions;
import com.user.entity.Users;
import com.user.repository.IUsersRepository;
import com.user.service.IUserService;
import com.user.utility.JWTUtility;

@SpringBootTest
public class UserControllerTest {
	
	@Autowired
	private UserController userController;
	
	@MockBean
	private IUsersRepository userRepo;
	
	@MockBean
	private IUserService userService;
		
	@MockBean
	private JWTUtility jwtUtility;
		
	Users userObj1;
	private JwtRequest jwtRequest;
	private Questions questionObj1 ;
	private Answers answerObj1;
	String token; 
	
	@BeforeEach
	void setUp() {
		token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOZXd1c2VyM0BnbWFpbC5jb20iLCJleHAiOjE2NzQ4Mjg4ODMsImlhdCI6MTY3NDgxMDg4M30.ntsaLwaEoQ_0f2hx1KZAaYkEbLSZsJ7Tu2XKB4SJjo33X5eqtUdi9UWV9L0tBt3FjwvnOqV6vIy78NprCb8lRg";
		userObj1 = new Users(1, "Vyshnavi", "Shriram", "Vyshnavi@gmail.com", "Vyshnavi@1", new Date());
		questionObj1 = new Questions(1, "Abot Java?", "What is a Eclipse?", 2, new Date());
		answerObj1 = new Answers(1, "Java is a programming Language", 1, 1, new Date(),"Vyshnavi Shriram");
		jwtRequest = new JwtRequest("Vyshnavi@gmail.com", "Vyshnavi@1");
	}
	
	@Test
	void registerUserTest() {
		when(userService.createUser(userObj1)).thenReturn(userObj1.getUserId());
		ResponseEntity<String> response = new ResponseEntity<String>("Registration Successful", HttpStatus.OK);
		assertEquals(response, userController.registerUser(userObj1));
	}
	
	@Test
	void loginUserTest() throws Exception {
		//Mockito.lenient().when(userController.loginUser(jwtRequest)).thenReturn(null);
	}
	
	@Test
	void addNewQuestionTest(){
		Mockito.lenient().when(userController.addNewQuestion(token,questionObj1)).thenReturn(null);
	}
	
	@Test
	void addNewAnswerTest(){
		Mockito.lenient().when(userController.addNewAnswer(token,answerObj1)).thenReturn(null);
	}
	
	@Test
	void getAllQuestionsTest(){
		Mockito.lenient().when(userController.getAllQuestions(token)).thenReturn(null);
	}
	
	
	@Test
	void getUserIdfromHeaderTest() {
		Mockito.lenient().when(userController.getUserIdfromHeader(token)).thenReturn(null);
		
	}

}
