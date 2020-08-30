package com.stockmarket.auth.models;

public class JwtAuthenticationResponse {

	@Override
	public String toString() {
		return "JwtAuthenticationResponse [accessToken=" + accessToken + ", tokenType=" + tokenType + "]";
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public JwtAuthenticationResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	private String accessToken;
	private final String tokenType = "Bearer";
}
