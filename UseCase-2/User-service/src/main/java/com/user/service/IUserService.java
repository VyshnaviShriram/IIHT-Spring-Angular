package com.user.service;


import com.user.entity.Users;

public interface IUserService {
	
	Integer createUser(Users user);

	Integer getUserIdByEmail(String userName);

	String getNamebyUserId(Integer userId);

}
