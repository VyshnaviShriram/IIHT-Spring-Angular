package com.book.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.entity.Book;

@Repository
public interface IBookRepo extends JpaRepository<Book, Integer> {

	@Query("select b from Book b where b.price =:price and b.category=:category and b.title=:title and b.author=:author and b.publisher=:publisher and b.active='Yes'")
	List<Book> getBooks(@Param("category") String category, @Param("title") String title,
			@Param("author") String author, @Param("price") Integer price, @Param("publisher") String publisher);
	
	@Query("select b.active from Book b where b.bookid=:bookId")
	String getStatusofBook(@Param("bookId") Integer bookId);

	List<Book> findByAuthorid(Integer authorId);
	
	
	@Query("select b from Book b where b.authorid=:userID and b.bookid=:bookId")
	Book findByAuthoridBookId(@Param("userID") Integer userID, @Param("bookId") Integer bookID);

	

}
