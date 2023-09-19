package com.book.service;

import java.util.*;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.entity.UserBookData;
import com.book.exception.ResourceNotFoundExceptionHandler;
import com.book.repository.IBookRepo;
import com.book.repository.IUserBookDataRepo;

@Service
public class BookServiceImpl implements IBookService{
	
	@Autowired
	private IBookRepo bookRepo;
	
	@Autowired
	private IUserBookDataRepo userBookRepo;
	
	@Override
	public List<Book> getBooks(Book book) {
		return bookRepo.getBooks(book.getCategory(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getPublisher());
	}

	@Override
	public Integer createBook(Book book, Integer userID) {	
		book.setAuthorid(userID);
		Book newBook =bookRepo.save(book);		
		return newBook.getBookid();
		
	}

	@Override
	public Book editBook(Book book, Integer userID, Integer bookID) {
		Book oldBook = bookRepo.findByAuthoridBookId(userID,bookID);
		if(null != oldBook) {
			oldBook.setLogo(book.getLogo());
			oldBook.setTitle(book.getTitle());
			oldBook.setCategory(book.getCategory());
			oldBook.setPrice(book.getPrice());
			oldBook.setAuthor(book.getAuthor());
			oldBook.setPublisher(book.getPublisher());
			oldBook.setReleaseDate(book.getReleaseDate());
			oldBook.setContent(book.getContent());
			oldBook.setActive(book.getActive());
			bookRepo.save(oldBook);
			return oldBook;
		}else {
			throw new ResourceNotFoundExceptionHandler("Book and Author combination", " for book with id", bookID);
		}
	}

	@Override
	public String editStatusofBook(Integer bookID) {
		Book oldBook = bookRepo.findById(bookID).orElseThrow(()->new ResourceNotFoundExceptionHandler("Book", "id", bookID));
		String status = (oldBook.getActive().equals("Yes") || oldBook.getActive().equals("Y"))?"No":"Yes";
		oldBook.setActive(status);
		bookRepo.save(oldBook);
		return status;
	}

	@Override
	public Book getBookbyId(Integer bookID) {
		// TODO Auto-generated method stub
		Book book = bookRepo.findById(bookID).orElseThrow(()->new ResourceNotFoundExceptionHandler("Book", "id", bookID));
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

	@Override
	public List<String> getBookListByUserID(Integer userId) {
		List<UserBookData> userBookList = userBookRepo.findByUserId(userId);
		List<String> books = new ArrayList<>();
		if(!userBookList.isEmpty()) {
			books=  userBookList.stream()
					.map(userBook->{
						int bookId = userBook.getBookId();
						int subscriptionId = userBook.getId();
						long subscriptionTimeToMillis = userBook.getSubscriptionDate().getTime();
						long subTimeCheck = subscriptionTimeToMillis + (24*60*60*1000);	//Adding 24hrs to subscription time
						boolean canUnsubscribe = false;
						long currentTime = new Date().getTime();
						if(subTimeCheck >= currentTime) canUnsubscribe =true;
						JSONObject obj = new JSONObject();
						Book book = getBookbyId(bookId);
						obj.put("bookid", bookId);
						obj.put("title", book.getTitle());
						obj.put("author", book.getAuthor());
						obj.put("publisher", book.getPublisher());
						obj.put("releaseDate", book.getReleaseDate());
						obj.put("category", book.getCategory());
						obj.put("logo", book.getLogo());
						obj.put("price", book.getPrice());
						obj.put("active", book.getActive());
						obj.put("content", book.getContent());
						obj.put("subscriptionId", subscriptionId);
						obj.put("subscriptionDt", userBook.getSubscriptionDate());
						obj.put("canunsubscribe", canUnsubscribe);	
						return obj.toString();
						})
					.collect(Collectors.toList());
		}
		return books;
	}

	@Override
	public Integer subscribe(Integer userId, Integer bookId) {
		UserBookData userBook = new UserBookData();
		Integer subscriptionId = userBookRepo.getsubscriptionID(userId, bookId);
		if(subscriptionId == null) {
			userBook.setBookId(bookId);
			userBook.setUserId(userId);
			userBook.setSubscriptionDate(new Date());
			userBook.setSubscriptionStatus("Active");
			UserBookData newUserBook = userBookRepo.save(userBook);
			return newUserBook.getId();
		}else {
			return 0;
		}
	}

	@Override
	public String getBookStatus(Integer bookId) {
		String status = bookRepo.getStatusofBook(bookId);
		return status;
	}

	@Override
	public Book getBookbySubscriptionId(Integer userId, Integer subscriptionId) {
		UserBookData userBook = userBookRepo.findByUserIdAndSubscriptionId(userId,subscriptionId);
		if(null != userBook)
			return getBookbyId(userBook.getBookId());
		else
			throw new ResourceNotFoundExceptionHandler("Book", "subscriptionId", subscriptionId);
	}

	@Override
	public UserBookData getUserBookData(Integer userId, Integer subscriptionId) {
		UserBookData userBook = userBookRepo.findByUserIdAndSubscriptionId(userId,subscriptionId);
		return userBook;
	}

	@Override
	public void updateSubscriptionStatus(Integer subscriptionId) {
		userBookRepo.deleteById(subscriptionId);
	}

	@Override
	public List<Book> getAllBooksbyAuthorID(Integer authorId) {
		return bookRepo.findByAuthorid(authorId);
	}
	
	


}
