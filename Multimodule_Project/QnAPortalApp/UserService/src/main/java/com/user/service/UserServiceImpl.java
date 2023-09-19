package com.user.service;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.dto.Usersdto;
import com.user.entity.Users;
import com.user.repository.IUsersRepository;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUsersRepository userRepo;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private ModelMapper modelMapper;
	 
	 public String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }

	@Override
	public Integer createUser(Users user) {
		Integer userId = 0;
		user.setCreatedDateTime(new Date());
		user.setPassword(getEncodedPassword(user.getPassword()));
		boolean isExistingId = userRepo.findAll().stream().anyMatch(data->data.getEmailId().equalsIgnoreCase(user.getEmailId()));
		if(!isExistingId) {
			Users newUser = userRepo.save(user);
			userId = newUser.getUserId();			
		}
		return userId;
	}

	@Override
	public Integer getUserIdByEmail(String userName) {
		Users user = userRepo.findByEmailId(userName).get(0);
		return user.getUserId();
	}

	@Override
	public String getNamebyUserId(Integer userId) {
		Optional<Users> user = userRepo.findById(userId);
		String name = null;
		if(user.isPresent()) {
			name = user.get().getFirstName()+" "+user.get().getLastName();
		}
		return name;	
	}

	@Override
	public Users getuserByEmail(String userName) {
		Users user = userRepo.findByEmailId(userName).get(0);
		return user;
	}
	
	public Usersdto converToDto(Users user) {
		Usersdto usersDto = modelMapper.map(user, Usersdto.class);
		return usersDto;
	}
	
	public Users converToEntity(Usersdto usersDto) {
		Users user = modelMapper.map(usersDto, Users.class);
		return user;
	}

}
