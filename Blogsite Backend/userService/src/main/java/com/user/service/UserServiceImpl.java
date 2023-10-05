package com.user.service;

import static com.user.entity.User.SEQUENCE_NAME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repository.IUsersRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUsersRepository userRepo;
	
	@Autowired
	SequenceGenratorService seqGenService;
	
	@Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 public String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }
	
	@Override
	public Integer createUser(User user) {
		Integer userId = 0;
		user.setCreatedDate(new Date());
		user.setPassword(getEncodedPassword(user.getPassword()));
		boolean isExistingId = userRepo.findAll().stream().anyMatch(data->data.getUserEmail().equalsIgnoreCase(user.getUserEmail()));
		if(!isExistingId) {
			user.setUserId(seqGenService.getSequenceNumber(SEQUENCE_NAME));
			User newUser = userRepo.save(user);
			userId = newUser.getUserId();			
		}
		return userId;
	}
	
	@Override
	public List<User> getUsersbyEmailId(String emailId){
		return userRepo.findByEmailId(emailId);
	}

	@Override
	public Integer getUserIdByEmail(String userName) {
		List<User> users = userRepo.findByEmailId(userName);
		return users.get(0).getUserId();
	}

}
