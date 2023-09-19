package com.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import static com.user.entity.User.*;
import com.user.repository.IUserRepositiry;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepositiry userRepo;
	
	@Autowired
	SequenceGenratorService seqGenService;
	
	@Override
	public Integer createUser(User user) {
		Integer userId = 0;
		user.setCreatedDate(new Date());
		//user.setPassword(getEncodedPassword(user.getPassword()));
		boolean isExistingId = userRepo.findAll().stream().anyMatch(data->data.getUserEmail().equalsIgnoreCase(user.getUserEmail()));
		if(!isExistingId) {
			user.setUserId(seqGenService.getSequenceNumber(SEQUENCE_NAME));
			User newUser = userRepo.save(user);
			userId = newUser.getUserId();			
		}
		return userId;
	}

}
