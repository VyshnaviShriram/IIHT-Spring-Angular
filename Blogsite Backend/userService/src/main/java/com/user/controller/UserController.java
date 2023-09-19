package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.IUserService;

@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user){
		String status = "";
		if(userService.createUser(user) == 0) {
			status = "Existing User, Please try with new EmailId";
		}else {
			status = "Registration Successful";
		}
		ResponseEntity<String> response = new ResponseEntity<String>(status, HttpStatus.OK);
		return response;
	}

}
