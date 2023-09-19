package com.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.entity.JwtRequest;
import com.user.entity.JwtResponse;
import com.user.entity.Users;
import com.user.repository.IUsersRepository;
import com.user.utility.JWTUtility;

@Service
public class JwtUserService implements UserDetailsService{
	
	@Autowired
	private JWTUtility jwtUtils;
	
	@Autowired
	private IUsersRepository userRepo;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	//public static final String BASE_URL = "http://localhost:5000/";
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		System.out.println("========User Repository =============="+userRepo);
		/*List<Users> users = userRepo.findByEmailId(username);
		Users user = null;
		if(users.size() > 0) {
			user = users.get(0);
		}*/
		Users user = userService.getuserByEmail(username);
		//Users user = restTemplate.getForObject(BASE_URL + "getUser/"+username, Users.class);
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
					getAuthority(user));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	
	private Set getAuthority(Users user) {
		Set authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE"));
		return authorities;
	}

	public JwtResponse createJWTToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getEmailId();
		String password = jwtRequest.getPassword();
		authenticate(userName, password);
		List<Users> users = userRepo.findByEmailId(userName);
		Users user = null;
		if(users.size() > 0) {
			user = users.get(0);
		}
		//Users user = restTemplate.getForObject(BASE_URL + "getUser/"+userName, Users.class);
		UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtils.generateToken(userDetails,user.getUserId().toString());
		return new JwtResponse(user, newGeneratedToken);
	}
		
	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
}
