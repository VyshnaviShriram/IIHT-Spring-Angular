package com.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Library {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String bookname;
	private String bookauthor;
	private float bookprice;
	private String bookgenre;
	private boolean borrowedstatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer bookID) {
		id = bookID;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookName) {
		bookname = bookName;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookAuthor) {
		bookauthor = bookAuthor;
	}
	public float getBookprice() {
		return bookprice;
	}
	public void setBookprice(float bookPrice) {
		bookprice = bookPrice;
	}
	public String getBookgenre() {
		return bookgenre;
	}
	public void setBookgenre(String bookGenre) {
		bookgenre = bookGenre;
	}
	public boolean isBorrowedstatus() {
		return borrowedstatus;
	}
	public void setBorrowedstatus(boolean borrowedStatus) {
		borrowedstatus = borrowedStatus;
	}
	public Library(Integer bookID, String bookName, String bookAuthor, float bookPrice, String bookGenre,
			boolean borrowedStatus) {
		super();
		id = bookID;
		bookname = bookName;
		bookauthor = bookAuthor;
		bookprice = bookPrice;
		bookgenre = bookGenre;
		borrowedstatus = borrowedStatus;
	}
	public Library() {
		super();
	}
	public Library(String bookName, String bookAuthor, float bookPrice, String bookGenre, boolean borrowedStatus) {
		super();
		bookname = bookName;
		bookauthor = bookAuthor;
		bookprice = bookPrice;
		bookgenre = bookGenre;
		borrowedstatus = borrowedStatus;
	}
	
	

}
