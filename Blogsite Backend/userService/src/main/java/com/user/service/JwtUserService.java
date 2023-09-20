package com.user.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repository.IUsersRepository;

@Service
public class JwtUserService implements UserDetailsService{
	
	/*@Autowired
	private JWTUtility jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;*/
	
	@Autowired
	private IUsersRepository userRepo;

	
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmailId(username).get(0);
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(),
					getAuthority(user));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set getAuthority(User user) {
		Set authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE"));
		return authorities;
	}

	/*public JwtResponse createJWTToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getEmailId();
		String password = jwtRequest.getPassword();
		authenticate(userName, password);
		UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtils.generateToken(userDetails);
		Users user = userRepo.findByEmailId(userName).get(0);
		return new JwtResponse(user, newGeneratedToken);
	}

	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
		throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	} */
	
}