package com.user.utility;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtility implements Serializable{
		
	private static final long serialVersionUID = 12345L;
	
	public static final long JWT_TOKEN_VALIDITY= 5*60*60;
	
	private String secretKey="Vyshnavi";
	
	public String getUserIdFromToken(String token) {
		return getClaimsFromToken(token, Claims::getId);
	}
	
	public String getUserNameFromToken(String token) {
		return getClaimsFromToken(token, Claims::getSubject);
	}
	
	private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
	
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimsFromToken(token, Claims::getExpiration);
	}
	
	public String generateToken(UserDetails userDetails, String userId) {
		Map<String,Object> claims = new HashMap();
		return doGenerateToken(claims, userDetails.getUsername(),userId);		
	}

	private String doGenerateToken(Map<String, Object> claims, String username, String userId) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setId(userId)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String userName=getUserNameFromToken(token);
		return (userName.equals(userDetails.getUsername()) && ! isTokenExpired(token));
	}

}
