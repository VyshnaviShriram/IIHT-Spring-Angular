package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.entity.Blog;
import com.user.entity.JwtRequest;
import com.user.entity.JwtResponse;
import com.user.entity.User;
import com.user.repository.IUsersRepository;
import com.user.service.IUserService;
import com.user.service.JwtUserService;
import com.user.utility.JWTUtility;

@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JwtUserService jwtUserService;
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private IUsersRepository userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	public static final String BASE_URL = "http://localhost:9091/";
	
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
	
	@PostMapping("/login")
	public JwtResponse loginUser(@RequestBody JwtRequest jwtRequest) throws Exception{
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
		User user = userRepo.findByEmailId(userName).get(0);
		JwtResponse jwtResponse = new JwtResponse(user, newGeneratedToken);
	
		return jwtResponse;
	}
	
	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog) {
		String message = restTemplate.postForObject(BASE_URL+"/addBlog", blog, String.class);
		ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getAllBlogs")
	public ResponseEntity<List<Blog>> getAllBlogs(@RequestBody Blog blog) {
		List<Blog> allBlogs= restTemplate.getForObject(BASE_URL+"/getAllBlogs", List.class);
		ResponseEntity<List<Blog>> response = new ResponseEntity<List<Blog>>(allBlogs, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getBlogsByCategory/{category}")
	public ResponseEntity<List<Blog>> getAllBlogsByCategory(@PathVariable String category) {
		List<Blog> allBlogs= restTemplate.getForObject(BASE_URL+"/getBlogsByCategory/"+category, List.class);
		ResponseEntity<List<Blog>> response = new ResponseEntity<List<Blog>>(allBlogs, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getBlogsByTime/{category}/{startTime}/{endTime}")
	public ResponseEntity<List<Blog>> getAllBlogsByCategoryANdTime(@PathVariable String category, @PathVariable String fromTime,@PathVariable String toTime) {
		List<Blog> allBlogs= restTemplate.getForObject(BASE_URL+"/getBlogsByCategory/"+category+"/"+fromTime+"/"+toTime, List.class);
		ResponseEntity<List<Blog>> response = new ResponseEntity<List<Blog>>(allBlogs, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/deleteBlogByName/{blogName}")
	public ResponseEntity<String> deleteBlogByName(@PathVariable String blogName) {
		List<Blog> allBlogs= restTemplate.getForObject(BASE_URL+"/getBlogsByName/"+blogName, List.class);
		ResponseEntity<String> response = null;
		String message = "";
		if(allBlogs.size() > 0) {
			restTemplate.delete(BASE_URL+"/deleteBlog/"+blogName);
			message = "Blog deleted successfully";
		}else {
			message = "Blog with the given name is not found";
		}
		response = new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}

}
