package com.blog.service;

import static com.blog.entity.Blog.SEQUENCE_NAME;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Blog;
import com.blog.repository.IBlogRepository;

@Service
public class BlogServiceImpl implements IBlogService{

	@Autowired
	private IBlogRepository blogRepo;
	
	@Autowired
	private SequenceGenratorService seqGenService;
	
	@Override
	public Integer createBlog(Blog blog) {
		Integer blogId= 0;
		blog.setBlogId(seqGenService.getSequenceNumber(SEQUENCE_NAME));
		blog.setCreatedTime(new Date());
		Blog newBlog = blogRepo.save(blog);
		blogId = newBlog.getBlogId();
		return blogId;
	}

	@Override
	public List<Blog> getAllBlogs() {
		// TODO Auto-generated method stub
		return blogRepo.findAll();
	}

	@Override
	public List<Blog> getBlogsbyCategory(String category) {
		List<Blog> blogs = blogRepo.findByCategory(category);
		return blogs;
	}

	@Override
	public Integer deleteBlogByName(String blogName) {
		List<Blog> deletedBlogs = blogRepo.deleteByName(blogName);
		return deletedBlogs.size();
	}

	@Override
	public List<Blog> getBlogsbyCategoryAndTimeRange(String category, Date startTime, Date endTime) {
		List<Blog> blogs = blogRepo.findByCategoryAndTimeRange(category,startTime,endTime);
		return blogs;
	}

	@Override
	public List<Blog> getBlogsbyName(String blogName) {
		List<Blog> blogs = blogRepo.findByName(blogName);
		return blogs;
	}

}
