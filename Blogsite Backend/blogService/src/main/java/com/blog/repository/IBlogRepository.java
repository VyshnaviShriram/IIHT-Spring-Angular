package com.blog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.blog.entity.Blog;

@Repository
public interface IBlogRepository extends MongoRepository<Blog, Integer>{

	@Query("{category:?0}")
	List<Blog> findByCategory(String category);
	
	@Query(value="{blogName:?0}",delete=true)
	List<Blog> deleteByName(String blogName);

	@Query("{category:?0,createdTime:{$gt:?1},createdTime:{$lt:?2}}")
	List<Blog> findByCategoryAndTimeRange(String category, Date startTime, Date endTime);

	@Query("{blogName:?0}")
	List<Blog> findByName(String blogName);

}
