package com.token.entity;

public class JWTResponse {
	
	private String JwtToken;

	public String getJwtToken() {
		return JwtToken;
	}

	public void setJwtToken(String jwtToken) {
		JwtToken = jwtToken;
	}

	public JWTResponse(String jwtToken) {
		super();
		JwtToken = jwtToken;
	}

	public JWTResponse() {
		super();
	}

	
}
