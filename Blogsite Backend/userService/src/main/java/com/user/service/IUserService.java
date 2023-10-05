package com.user.service;

import java.util.List;

import com.user.entity.User;

public interface IUserService {

	Integer createUser(User user);

	List<User> getUsersbyEmailId(String emailId);

	Integer getUserIdByEmail(String userName);
}
