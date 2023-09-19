package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repository.IUserRepositiry;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepositiry userRepo;
	
	@Override
	public Integer createUser(User user) {
		User newUser = userRepo.save(user);
		return newUser.getUserId();
	}

}
