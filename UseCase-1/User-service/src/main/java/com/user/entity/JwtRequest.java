package com.user.entity;

public class JwtRequest {
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JwtRequest(String userName, String password) {
		super();
		this.username = userName;
		this.password = password;
	}
	public JwtRequest() {
		super();
	}

	
}
