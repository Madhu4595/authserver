package com.authserver.model;

public class ValidateTokenRequestModel {
	
	private String secretKey;
	private String jwtToken;
	public ValidateTokenRequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ValidateTokenRequestModel(String secretKey, String jwtToken) {
		super();
		this.secretKey = secretKey;
		this.jwtToken = jwtToken;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	@Override
	public String toString() {
		return "ValidateTokenRequestModel [secretKey=" + secretKey + ", jwtToken=" + jwtToken + "]";
	}
	
	

}
