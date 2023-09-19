package com.user.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.Usersdto;
import com.user.entity.JwtRequest;
import com.user.entity.JwtResponse;
import com.user.entity.Users;
import com.user.service.IUserService;
import com.user.service.JwtUserService;
import com.user.utility.JWTUtility;

@CrossOrigin("*")
@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private JwtUserService jwtUserService;
		
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
		JwtResponse jwtResponse = jwtUserService.createJWTToken(jwtRequest);
		return jwtResponse;
	}
	
	@GetMapping("/getUser/{userName}")
	public Users getUserbyUserName(@PathVariable("userName") String userName) {
		Users user = null;
		user = userService.getuserByEmail(userName);
		return user;
	}
	
	@GetMapping("/getNameofUser/{userId}")
	public String getNamebyUserId(@PathVariable("userId") Integer userId) {
		String user = userService.getNamebyUserId(userId);
		return user;
	}
	
	/* @PostMapping("/addQuestion")
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
	
	@GetMapping("/getAllQuestionsById")
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
	} 

	private Integer getUserIdfromHeader(String authHeader) {
		String token = null;
		String userName = null;
		Integer userId = null;
		if(null != authHeader && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			userName = jwtUtility.getUserNameFromToken(token);
			userId = userService.getUserIdByEmail(userName);
		}
		return userId;
	}*/
}
