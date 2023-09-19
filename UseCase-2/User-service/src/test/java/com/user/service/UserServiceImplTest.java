package com.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.user.entity.Users;
import com.user.repository.IUsersRepository;
import com.user.service.IUserService;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private IUserService userService;
	
	@MockBean
	private IUsersRepository userRepo;
	
	public Users userObj1;
	
	@BeforeEach
	void setUp() {
		userObj1= new Users(1,"Vyshnavi","Shriram","vyshnavi@gmail.com","vyshnavi",new Date());
	}
	
	@Test
	void createUserTest() {
		when(userRepo.save(userObj1)).thenReturn(userObj1);
		assertEquals(1, userService.createUser(userObj1));
	}
	
	@Test
	void getUserIdByEmailTest() {
		when(userRepo.findByEmailId("vyshnavi@gmail.com")).thenReturn(List.of(userObj1));
		assertEquals(1, userService.getUserIdByEmail("vyshnavi@gmail.com"));
	}
	
	@Test
	void getNamebyUserIdTest() {
		when(userRepo.findById(1)).thenReturn(Optional.of(userObj1));
		assertEquals("Vyshnavi Shriram", userService.getNamebyUserId(1));
		
	}
	

}
