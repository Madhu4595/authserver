package com.authserver.exception;

import java.util.Date;

public class ErrorResponse {
	private Date timestamp;
    private String message;
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ErrorResponse [timestamp=" + timestamp + ", message=" + message + "]";
	}
    
    
}
