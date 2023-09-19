package com.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface IUserRepositiry extends MongoRepository<User, Integer>{

}
