package com.stockmarket.auth.models;

public class AuthenticateRequest {
	private String userName;

	public AuthenticateRequest() {
		super();
	}

	public AuthenticateRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	private String password;
}
