package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repository.IUserRepo;

@Service
public class UserServiceImpl implements IUserService{
	
//	List<User> user = List.of(
//			new User("Vyshnavi","Vyshu","Author"),
//			new User("Manju","Manju","Reader"),
//			new User("Kathyayani","Kathyayani","Author"),
//			new User("Vyshnavi Shriram","Shriram","Reader"),
//			new User("Vyshu","Vyshnavi","Author"),
//			new User("Niharika","Shriram","Reader"));
	
	@Autowired
	private IUserRepo userRepo;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 public String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }

	@Override
	public long getSignInDetails(String userName,String password) {
		return userRepo.findAll().stream().filter(user->user.getUsername().equalsIgnoreCase(userName) && user.getPassword().equalsIgnoreCase(password)).count();
		//return user.stream().filter(user->{return user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equals(password);}).count();
	}

	@Override
	public String isExistingUser(User userData) {
		String userName = userData.getUsername();
		boolean isPresent = false;
		if(null != userRepo)
			isPresent = userRepo.findAll().stream().filter(user->user.getUsername().equals(userName)).findAny().isPresent();
		if(!isPresent) {
			String password = userData.getPassword();
			password = getEncodedPassword(password);
			userData.setPassword(password);
			userRepo.save(userData);
			return "Success";
		}else {
			return "Failure";
		}
	}

	@Override
	public String getRolebyId(Integer id) {
		return userRepo.getRole(id);
	}

}
