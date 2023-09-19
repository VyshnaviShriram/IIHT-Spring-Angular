package com.user.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.Users;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Integer>{
	List<Users> findByEmailId(String emailId);

}
