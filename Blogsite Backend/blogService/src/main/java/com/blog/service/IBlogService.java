package com.blog.service;

import java.util.Date;
import java.util.List;

import com.blog.entity.Blog;

public interface IBlogService {

	Integer createBlog(Blog blog);

	List<Blog> getAllBlogs();

	List<Blog> getBlogsbyCategory(String category);

	Integer deleteBlogByName(String blogName);

	List<Blog> getBlogsbyCategoryAndTimeRange(String category, Date startTime, Date endTime);

	List<Blog> getBlogsbyName(String blogName);

}
