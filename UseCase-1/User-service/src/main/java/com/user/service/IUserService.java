package com.user.service;

import com.user.entity.User;

public interface IUserService {

	long getSignInDetails(String userName,String password);
	String isExistingUser(User userData);
	String getRolebyId(Integer id);

}
