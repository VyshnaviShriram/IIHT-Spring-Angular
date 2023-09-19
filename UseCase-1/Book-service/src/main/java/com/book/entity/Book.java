package com.book.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bookid;
	private Integer authorid;
	private String title;
	private String author;		//Same as UserName
	private String publisher;
	@Temporal(value = TemporalType.DATE)
	private Date releaseDate;
	//\"yyyy-MM-dd'T'HH:mm:ss.SSSX\", \"yyyy-MM-dd'T'HH:mm:ss.SSS\", \"EEE, dd MMM yyyy HH:mm:ss zzz\", \"yyyy-MM-dd\")
	private String category;
	private String logo;		//Logo of Book
	private Integer price;
	private String active;		//Book is Blocked/Unblocked
	private String content;		//URL of Book
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	
	public Integer getAuthorid() {
		return authorid;
	}
	public void setAuthorid(Integer authorid) {
		this.authorid = authorid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Book(Integer bookid, String title, String author, String publisher, Date releaseDate, String category,
			String logo, Integer price, String active, String content) {
		super();
		this.bookid = bookid;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.releaseDate = releaseDate;
		this.category = category;
		this.logo = logo;
		this.price = price;
		this.active = active;
		this.content = content;
	}
	public Book() {
		super();
	}
	
	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", releaseDate=" + releaseDate + ", category=" + category + ", logo=" + logo + ", price=" + price
				+ ", active=" + active + ", content=" + content + "]";
	}
	
}
