package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {

	@Query("select u.role from User u where u.userid=:id")
	String getRole(@Param("id") Integer id);

//	@Query(value="select * from User where username=:username",nati)
//	User findByUserName(String username);
	
	List<User> findByUsername(String username);

}
