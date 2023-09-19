package com.user.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.user.entity.Book;
import com.user.entity.JwtRequest;
import com.user.entity.JwtResponse;
import com.user.entity.User;
import com.user.entity.UserBookData;
import com.user.service.IUserService;
import com.user.service.JwtUserService;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JwtUserService jwtUserService;
	
	public static final String BASE_URL = "http://BOOK-SERVICE/book/";
	
	//For Signing in
	@PostMapping("/sign-in")
	public JwtResponse verifySignIn(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtUserService.createJwtToken(jwtRequest);
	}
	
	//For signing up(creating an Account)
	@PostMapping(value = "/sign-up", produces = "application/json")
	public ResponseEntity<String> createAccount(@RequestBody User userData) {
		String result;
		HttpStatus status;
		result=userService.isExistingUser(userData);
		if(result.equals("Success")) {
			result = "Account created successfully, please Sign In";
			status = HttpStatus.OK;
		}else {
			result="Username is existing, so please try with different username or signin";
			status = HttpStatus.FORBIDDEN;
		}
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, status);
		return responseEntity;
	}
	
	//Get Role of user by userId
//	@GetMapping("/getRole/{id}")
//	public String getRoleByID(@PathVariable("id") Integer id) {
//		return userService.getRolebyId(id);
//	}
	
	//To fetch all the books subscribed by Reader
	@GetMapping("/readers/{userid}/books")
	public ResponseEntity<List<String>> getListofBooksSuscribed(@PathVariable("userid") Integer userid){
		@SuppressWarnings("unchecked")
		List<String> book = restTemplate.getForObject(BASE_URL+"/booksSubscribedbyId/"+userid, List.class);
		return new ResponseEntity<List<String>>(book, HttpStatus.OK);
	}
	
	//To subscribe a book by the Reader
	@PostMapping("/readers/{bookId}/subscribingBook")
	public Integer subscribeBook(@RequestBody User request,@PathVariable("bookId") Integer bookId) {
		Integer userId = request.getUserid();{
		return restTemplate.getForObject(BASE_URL+"/subscribe/"+bookId+"/"+userId, Integer.class);
		}	
	}
	
	//To fetch a specific book subscribed by Reader
	@GetMapping("/readers/{userId}/books/{subscriptionId}")
	public Book getSubscribedBook(@PathVariable("userId") Integer userId,@PathVariable("subscriptionId") Integer subscriptionId) {
		 Book book = restTemplate.getForObject(BASE_URL+"/subscribedBook/"+userId+"/"+subscriptionId, Book.class);
		return book;
	}
	
	//To get the content of a book subscribed by Reader
	@GetMapping("/readers/{userId}/books/{subscriptionId}/read")
	public String getSubscribedBookContent(@PathVariable("userId") Integer userId,@PathVariable("subscriptionId") Integer subscriptionId) {
		 Book book = restTemplate.getForObject(BASE_URL+"/subscribedBook/"+userId+"/"+subscriptionId, Book.class);
		 String content = book.getContent();
		return content;
	}
	
	//TO Cancel subscription of a book by reader within 24hrs
	@PostMapping("/readers/{userId}/books/{subscriptionId}/read/cancel-subscription")
	public String cancelSubscription(@PathVariable("userId") Integer userId,@PathVariable("subscriptionId") Integer subscriptionId) {
		 UserBookData userBook = restTemplate.getForObject(BASE_URL+"/getUserBookData/"+userId+"/"+subscriptionId, UserBookData.class);
		 Date subscriptionTime = userBook.getSubscriptionDate();
		 long subscriptionTimeToMillis = subscriptionTime.getTime();
		 long subTimeCheck = subscriptionTimeToMillis + (24*60*60*60*1000);	//Adding 24hrs to subscription time
		 String subscriptionStatus = null;
		 long currentTime = new Date().getTime();
		 if(subTimeCheck >= currentTime) {		
		 	restTemplate.delete(BASE_URL+"/cancelSubscription/"+subscriptionId);
		 	subscriptionStatus = "Cancelled Subscription";
		 }else
			 subscriptionStatus = "Can't camcel subscription";
		return subscriptionStatus;
	}
//	
//	// To search a book by author/Guest/Reader
//	@GetMapping("/search")
//	public List<Book> searchBooks(@RequestParam("category") String category, @RequestParam("title") String title,
//			@RequestParam("author") String author, @RequestParam("price") Integer price,
//			@RequestParam("publisher") String publisher) {
//		Book book = new Book();
//		book.setCategory(category);
//		book.setTitle(title);
//		book.setAuthor(author);
//		book.setPrice(price);
//		book.setPublisher(publisher);
//		@SuppressWarnings("unchecked")
//		List<Book> books = restTemplate.postForObject(BASE_URL+"/searchBook", book, List.class);
//		return books;
//	}
	
	//To get all books created by author
		@SuppressWarnings("unchecked")
		@GetMapping("/author/{authorID}/getbooks")
		public List<Book> getAllAuthorBook(@PathVariable("authorID") Integer userID){
			List<Book> books = restTemplate.getForObject(BASE_URL+"/getAll/"+userID,List.class);
			return books;
		}
		
		//TO fetch a book by BOOKID
		@GetMapping("/author/{bookid}/getbook")
		public Book getBookById(@PathVariable("bookid") Integer bookId) {
			Book book = restTemplate.getForObject(BASE_URL+"/searchbyId/"+bookId, Book.class);
			return book;
		}
	
	//TO add a book by author
	@PostMapping("/author/{authorID}/books")
	public Integer createBook(@PathVariable("authorID") String userID,@RequestBody Book book){
		Integer bookId = restTemplate.postForObject(BASE_URL+"/create/"+userID, book, Integer.class);
		return bookId;
	}
	
	//To edit a book by author
	@PutMapping("/author/{authorID}/books/{bookID}")
	public String editBook(@PathVariable("authorID") Integer userID, @PathVariable("bookID") Integer bookID,@RequestBody Book book) throws Exception{
		restTemplate.put(BASE_URL+"/edit/"+userID+"/"+bookID, book);
		return "Success";
	}
	
	//To change the status of book
	@PostMapping("/author/{authorID}/books/{bookID}/changeStatus")
	public ResponseEntity<String> changeBookStatus(@PathVariable("authorID") Integer userID, @PathVariable("bookID") Integer bookID) throws Exception{
		return new ResponseEntity<String>(restTemplate.postForObject(BASE_URL+"/changeBookStatus/"+bookID, null, String.class), HttpStatus.OK);
	}
}
