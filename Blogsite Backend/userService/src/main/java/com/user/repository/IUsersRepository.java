package com.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface IUsersRepository extends MongoRepository<User, Integer>{

	@Query("{userEmail:?0}")
	List<User> findByEmailId(String emailId);
}
