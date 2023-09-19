package com.book.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.book.entity.Book;
import com.book.entity.UserBookData;

@Service
public interface IBookService {

	List<Book> getBooks(Book book);

	Integer createBook(Book book, Integer userID);

	Book editBook(Book book, Integer userID, Integer bookID);

	String editStatusofBook(Integer bookID);
	
	Book getBookbyId(Integer bookID);

	List<Book> getAllBooks();

	List<String> getBookListByUserID(Integer userId);

	Integer subscribe(Integer userId, Integer bookId);
	
	String getBookStatus(Integer bookId);

	Book getBookbySubscriptionId(Integer userId, Integer subscriptionId);

	UserBookData getUserBookData(Integer userId, Integer subscriptionId);

	void updateSubscriptionStatus(Integer subscriptionId);

	List<Book> getAllBooksbyAuthorID(Integer authorId);
	

}
