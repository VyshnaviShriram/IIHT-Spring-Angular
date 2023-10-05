package com.blog.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Blog;
import com.blog.service.IBlogService;

@RestController
@RequestMapping("/blogs")
@CrossOrigin("*")
public class BlogController {

	@Autowired
	private IBlogService blogService;
	
	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog) {
		String status = "";
		if(blogService.createBlog(blog) == 0) {
			status = "Please try after some time";
		}else {
			status = "Blog added successfully";
		}
		ResponseEntity<String> response = new ResponseEntity<String>(status, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getAllBlogs")
	public ResponseEntity<List<Blog>> getAllBlogs(){
		List<Blog> blogs = blogService.getAllBlogs();
		ResponseEntity<List<Blog>> response = new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getBlogsByName/{blogName}")
	public ResponseEntity<List<Blog>> getAllBlogsbyName(@PathVariable String blogName){
		List<Blog> blogs = blogService.getBlogsbyName(blogName);
		ResponseEntity<List<Blog>> response = new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getBlogsByCategory/{category}")
	public ResponseEntity<List<Blog>> getAllBlogsbyCategory(@PathVariable String category){
		List<Blog> blogs = blogService.getBlogsbyCategory(category);
		ResponseEntity<List<Blog>> response = new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getBlogsByTime/{category}/{fromTime}/{toTime}")
	public ResponseEntity<List<Blog>> getAllBlogsbyCategoryInTimeRange(@PathVariable String category, @PathVariable String fromTime,@PathVariable String toTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = formatter.parse(fromTime+"T00:00:00.000+00:00");
			endTime = formatter.parse(toTime+"T00:00:00.000+00:00");
			//startTime = formatter.parse(fromTime);
			//endTime = formatter.parse(toTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Blog> blogs = blogService.getBlogsbyCategoryAndTimeRange(category,startTime,endTime);
		ResponseEntity<List<Blog>> response = new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/deleteBlog/{blogName}")
	public ResponseEntity<String> deleteBlogByName(@PathVariable String blogName) {
		String status = "";
		Integer deletedCount = blogService.deleteBlogByName(blogName);
		status = (deletedCount == 0) ? "Please Enter proper Blog Name" : "Blog with the given name is deleted" ;
		ResponseEntity<String> response = new ResponseEntity<String>(status,HttpStatus.OK);
		return response;
	}
}
