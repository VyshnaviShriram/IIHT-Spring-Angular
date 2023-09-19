package com.user.controller;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.entity.Answers;
import com.user.entity.JwtRequest;
import com.user.entity.JwtResponse;
import com.user.entity.Questions;
import com.user.entity.Users;
import com.user.repository.IUsersRepository;
import com.user.service.IUserService;
import com.user.service.JwtUserService;
import com.user.utility.JWTUtility;

@CrossOrigin("*")
@RestController
public class UserController {
	
	public static final String BASE_URL = "http://44.204.167.204:5001/QnA/"; 
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private JwtUserService jwtUserService;
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private IUsersRepository userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Users user){
		String status = "";
		if(userService.createUser(user) == 0) {
			status = "Existing User, Please try with new EmailId";
		}else {
			status = "Registration Successful";
		}
		ResponseEntity<String> response = new ResponseEntity<String>(status, HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/login")
	public JwtResponse loginUser(@RequestBody JwtRequest jwtRequest) throws Exception{
		//JwtResponse jwtResponse = jwtUserService.createJWTToken(jwtRequest);

		String userName = jwtRequest.getEmailId();
		String password = jwtRequest.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		UserDetails userDetails = jwtUserService.loadUserByUsername(userName);
		String newGeneratedToken = jwtUtility.generateToken(userDetails);
		Users user = userRepo.findByEmailId(userName).get(0);
		JwtResponse jwtResponse = new JwtResponse(user, newGeneratedToken);
	
		return jwtResponse;
	}
	
	@PostMapping("/addQuestion")
	public ResponseEntity<Integer> addNewQuestion(@RequestHeader("Authorization") String authHeader, @RequestBody Questions question) {
		Integer userId = getUserIdfromHeader(authHeader);
		question.setUserId(userId);
		Integer questionId = restTemplate.postForObject(BASE_URL+"postQuestion", question, Integer.class);
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(questionId, HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/addAnswer")
	public ResponseEntity<Integer> addNewAnswer(@RequestHeader("Authorization") String authHeader, @RequestBody Answers answer) {
		Integer userId = getUserIdfromHeader(authHeader);
		answer.setUserId(userId);
		Integer answerId = restTemplate.postForObject(BASE_URL+"postAnswer", answer, Integer.class);
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(answerId, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getAllQuestions")
	public ResponseEntity<List<Questions>> getAllQuestions(@RequestHeader("Authorization") String authHeader){
		List<Questions> response = restTemplate.getForObject(BASE_URL+"getAllQuestionsById", List.class);
		ObjectMapper mapper = new ObjectMapper();
		List<Questions> questions = new ArrayList<>();
		String arrayToJson = null;
		try {
			arrayToJson = mapper.writeValueAsString(response);
			TypeReference<List<Questions>> mapType = new TypeReference<List<Questions>>() {};
			questions = mapper.readValue(arrayToJson, mapType);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	questions = questions.stream().map(ques->{
			List<Answers> restAnswers = restTemplate.getForObject(BASE_URL+"getAnswersByQuestionsId/"+ques.getQuestionId(), List.class);
			List<Answers> answers = new ArrayList<>();
			String answersArrayToJson = null;
			try {
				answersArrayToJson = mapper.writeValueAsString(restAnswers);
				TypeReference<List<Answers>> mapType = new TypeReference<List<Answers>>() {};
				answers = mapper.readValue(answersArrayToJson, mapType);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answers= answers.stream().map(
					ans->{
						ans.setNameofUser(userService.getNamebyUserId(ans.getUserId()));
						return ans;
					}).collect(Collectors.toList());
			ques.setAnswers(answers);
			ques.setNameofUser(userService.getNamebyUserId(ques.getUserId()));
			System.out.println("Answers are  "+ques);
			return ques;
		}).collect(Collectors.toList());
    	ResponseEntity<List<Questions>> responseEntity = new ResponseEntity<List<Questions>>(questions, HttpStatus.OK);
		return responseEntity;
	}
	
	/*@GetMapping("/getAllQuestionsById")
	public ResponseEntity<List<Questions>> getAllQuestionsById(@RequestHeader("Authorization") String authHeader){
		Integer userId = null;
		userId = getUserIdfromHeader(authHeader);
		List<Questions> response = restTemplate.getForObject(BASE_URL+"getAllQuestionsById?userId="+userId, List.class);
		ObjectMapper mapper = new ObjectMapper();
		List<Questions> questions = new ArrayList<>();
		String arrayToJson = null;
		try {
			arrayToJson = mapper.writeValueAsString(response);
			TypeReference<List<Questions>> mapType = new TypeReference<List<Questions>>() {};
			questions = mapper.readValue(arrayToJson, mapType);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	questions = questions.stream().map(ques->{
			List<Answers> answers = restTemplate.getForObject(BASE_URL+"getAnswersByQuestionsId/"+ques.getQuestionId(), List.class);
			ques.setAnswers(answers);
			System.out.println("Answers are  "+ques);
			return ques;
		}).collect(Collectors.toList());
    	ResponseEntity<List<Questions>> responseEntity = new ResponseEntity<List<Questions>>(questions, HttpStatus.OK);
		return responseEntity;
	}*/

	public Integer getUserIdfromHeader(String authHeader) {
		String token = null;
		String userName = null;
		Integer userId = null;
		if(null != authHeader && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			userName = jwtUtility.getUserNameFromToken(token);
			userId = userService.getUserIdByEmail(userName);
		}
		return userId;
	}
}
