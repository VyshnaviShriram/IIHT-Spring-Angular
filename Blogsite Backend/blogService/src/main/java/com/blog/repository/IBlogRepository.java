package com.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Blog;

@Repository
public interface IBlogRepository extends MongoRepository<Blog, Integer>{

}
