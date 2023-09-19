package com.hr.entity;


public class Resource {
	
	private Long rID;
	private String email;
	private String resourceName;
	private String domain;
	
	private Long userID;

	public Resource(Long rID, String email, String resourceName, String domain, Long userID) {
		super();
		this.rID = rID;
		this.email = email;
		this.resourceName = resourceName;
		this.domain = domain;
		this.userID = userID;
	}

	public Resource() {
		super();
	}

	public Long getrID() {
		return rID;
	}

	public String getEmail() {
		return email;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getDomain() {
		return domain;
	}

	public Long getUserID() {
		return userID;
	}

	public void setrID(Long rID) {
		this.rID = rID;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
}
