package com.book.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.Book;
import com.book.entity.UserBookData;
import com.book.service.IBookService;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {
	
	@Autowired
	private IBookService bookService;

/*	@PostMapping("/searchBook")
	public List<Book> searchBooks(@RequestBody Book book) {
		System.out.println(book.toString());
		List<Book> books = bookService.getBooks(book);
		return books;
	}*/
	
	@GetMapping("/search")
	public List<Book> searchBooks(@RequestParam("category") String category, @RequestParam("title") String title,
			@RequestParam("author") String author, @RequestParam("price") Integer price,
			@RequestParam("publisher") String publisher) {
		Book book = new Book();
		book.setCategory(category);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(price);
		book.setPublisher(publisher);
		List<Book> books = bookService.getBooks(book);
		return books;
	}
	
	@GetMapping("/getAllBooks")
	public List<Book> searchBooks() {
		return bookService.getAllBooks();
	}
	
	@PostMapping("/create/{authorID}")
	public Integer createBook(@PathVariable("authorID") Integer userID,@RequestBody Book book){
		return bookService.createBook(book,userID);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/getAll/{authorid}")
	public ResponseEntity<List> getAllBooksByAuthor(@PathVariable("authorid") Integer authorId){
		List<Book> books = bookService.getAllBooksbyAuthorID(authorId);
		return new ResponseEntity<List>(books,HttpStatus.OK);
	}
	
	@PutMapping("/edit/{authorID}/{bookID}")
	public Book createBook(@PathVariable("authorID") Integer userID, @PathVariable("bookID") Integer bookID,@RequestBody Book book) throws Exception{
			return bookService.editBook(book,userID,bookID);
	}
	
	@PostMapping("/changeBookStatus/{bookID}")
	public String changeBookStatus(@PathVariable("bookID") Integer bookID) throws Exception{
		return bookService.editStatusofBook(bookID);
	}
	
	@GetMapping("/searchbyId/{bookid}")
	public Book searchBooks(@PathVariable("bookid") Integer bookId) {
		Book book = bookService.getBookbyId(bookId);
		return book;
	}
	
	@GetMapping("/booksSubscribedbyId/{userId}")
	public List<String> getListofBooksSubscribed(@PathVariable("userId") Integer userId){
		List<String> books = bookService.getBookListByUserID(userId);
		return books;
	}

	@GetMapping("/subscribe/{bookId}/{userId}")
	public Integer subscribeBook(@PathVariable("bookId") Integer bookId,@PathVariable("userId") Integer userId) throws Exception{
		Integer id = null;
		String status = bookService.getBookStatus(bookId);
		if(null != status && (status.equalsIgnoreCase("Y") || status.equalsIgnoreCase("Yes"))) {
			id = bookService.subscribe(userId,bookId);
		}
		if(id != null) {
			return id;
		}else {
			throw new NullPointerException("Only books which are Active can be subscribed.");
		}
		
	}
	
	@GetMapping("/subscribedBook/{userId}/{subscriptionId}")
	public Book bookSubscribed(@PathVariable("subscriptionId") Integer subscriptionId,@PathVariable("userId") Integer userId) {
		return bookService.getBookbySubscriptionId(userId,subscriptionId);
	}
	
	@GetMapping("/getUserBookData/{userId}/{subscriptionId}")
	public UserBookData getUserBookData(@PathVariable("subscriptionId") Integer subscriptionId,@PathVariable("userId") Integer userId) {
		return bookService.getUserBookData(userId,subscriptionId);
	}
	
	@DeleteMapping("/cancelSubscription/{subscriptionId}")
	public void updateSubscriptionStatus(@PathVariable("subscriptionId") Integer subscriptionId) {
		bookService.updateSubscriptionStatus(subscriptionId);
	}
}
