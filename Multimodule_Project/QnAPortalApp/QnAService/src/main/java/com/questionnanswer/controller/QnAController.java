package com.questionnanswer.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.questionnanswer.entity.Answers;
import com.questionnanswer.entity.Questions;
import com.questionnanswer.service.IQnAService;
import com.user.service.IUserService;
import com.user.utility.JWTUtility;

@RestController
@RequestMapping("/QnA")
@CrossOrigin("*")
public class QnAController {

	@Autowired
	private IQnAService questionnAnswerService;
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private IUserService userService;
	
	//@PostMapping("/postQuestion")
	@PostMapping("/addQuestion")
	public ResponseEntity<Integer> PostNewQuestion(@RequestHeader("Authorization") String authHeader, @RequestBody Questions question) {
		Integer userId = getUserIdfromHeader(authHeader);
		question.setUserId(userId);
		Integer questionId = questionnAnswerService.addQuestion(question);
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(questionId, HttpStatus.OK);
		return response;
	}
	
	//@PostMapping("/postAnswer")
	@PostMapping("/addAnswer")
	public ResponseEntity<Integer> PostNewAnswer(@RequestHeader("Authorization") String authHeader, @RequestBody Answers answer) {
		Integer answerId=null;
		Integer userId = getUserIdfromHeader(authHeader);
		answer.setUserId(userId);
		try {
			answerId = questionnAnswerService.addAnswer(answer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(answerId, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getAllQuestions")
	public ResponseEntity<List<Questions>> getAllQuestions(@RequestHeader("Authorization") String authHeader){
		List<Questions> questions = questionnAnswerService.getAllQuestions();
    	questions = questions.stream().map(ques->{
			List<Answers> answers = questionnAnswerService.getAllAnswersById(ques.getQuestionId());
			answers= answers.stream().map(
					ans->{
						String name = userService.getNamebyUserId(ans.getUserId());
						ans.setNameofUser(name);
						return ans;
					}).collect(Collectors.toList());
			ques.setAnswers(answers);
			//ques.setNameofUser(getUserNamefromHeader(authHeader));
			System.out.println("Answers are  "+ques);
			return ques;
		}).collect(Collectors.toList());
    	ResponseEntity<List<Questions>> responseEntity = new ResponseEntity<List<Questions>>(questions, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/getAllQuestionsById")
	public ResponseEntity<List<Questions>> getAllQuestionsById(@RequestHeader("Authorization") String authHeader){
		Integer userId = null;
		userId = getUserIdfromHeader(authHeader);
		List<Questions> questions = questionnAnswerService.getAllQuestionsById(userId);
    	questions = questions.stream().map(ques->{
			List<Answers> answers = questionnAnswerService.getAllAnswersById(ques.getQuestionId());
			answers= answers.stream().map(
					ans->{
						String name = userService.getNamebyUserId(ans.getUserId());
						ans.setNameofUser(name);
						return ans;
					}).collect(Collectors.toList());
			ques.setAnswers(answers);
			System.out.println("Answers are  "+ques);
			return ques;
		}).collect(Collectors.toList());
    	ResponseEntity<List<Questions>> responseEntity = new ResponseEntity<List<Questions>>(questions, HttpStatus.OK);
		return responseEntity;
	}
	
	/* @GetMapping("/getAllQuestionsById")
	public List<Questions> retrieveAllQuestionsById(@RequestParam("userId") Optional<Integer> userId){
		List<Questions> questions = null;
		if(userId.isPresent())
			questions = questionnAnswerService.getAllQuestionsById(userId.get());
		else
			questions = questionnAnswerService.getAllQuestions();
		return questions ;
	}
		
	@GetMapping("/getAnswersByQuestionsId/{questionId}")
	public List<Answers> retrieveAllAnswersById(@PathVariable("questionId") Integer questionId){
		List<Answers> answers = null;
		answers = questionnAnswerService.getAllAnswersById(questionId);
		return answers ;
	} */
	
	private Integer getUserIdfromHeader(String authHeader) {
		String token = null;
		Integer userId = null;
		if(null != authHeader && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			String id = jwtUtility.getUserIdFromToken(token);
			userId = Integer.parseInt(id);
		}
		return userId;
	} 
	
	/* private String getUserNamefromHeader(String authHeader) {
		String token = null;
		String userName = null;
		if(null != authHeader && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			userName = jwtUtility.getUserNameFromToken(token);
		}
		return userName;
	}
	
	private String getNamefromHeader(String authHeader) {
		String token = null;
		String userName = null;
		if(null != authHeader && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			userName = jwtUtility.getNameofUserFromToken(token);
		}
		return userName;
	}*/ 

}
