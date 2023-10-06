package com.authserver.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authserver.exception.ErrorResponse;
import com.authserver.jwt.JwtService;
import com.authserver.model.TokenRequestModel;
import com.authserver.model.TokenResponseModel;
import com.authserver.model.ValidateTokenRequestModel;

@RestController
public class HomeController {
	
	@Autowired private JwtService jwtService;
	
	@GetMapping("/hello")
	public String hello() { return "Hello from controller"; }
	
	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@RequestBody TokenRequestModel tokenRequestModel){
		System.out.println("secretkey=>"+tokenRequestModel.getSecretKey());
		String[] secretKeys = {"itiproject","eshramprojecteshramprojecteshramprojecteshramproject"};
		
		boolean find = false;
		for(int i = 0; i < secretKeys.length; i++) {
			if(tokenRequestModel.getSecretKey().equalsIgnoreCase(secretKeys[i])) {
				find = true;
			}
		}
		
		if(find) {
			TokenResponseModel tokenResponseModel = new TokenResponseModel();
			String token = jwtService.generateToken(tokenRequestModel);
			System.out.println("token=>"+token);
			tokenResponseModel.setJwtToken(token);
			
			return ResponseEntity.ok(tokenResponseModel);
		}else {
			ErrorResponse errorResponse = new ErrorResponse(new Date(), "SECRET KEY IS REQUIRED");
			return new ResponseEntity<ErrorResponse>(errorResponse,  HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
	
	
	@PostMapping("/validateToken")
	public ResponseEntity<?> validateToken(@RequestBody ValidateTokenRequestModel validateTokenRequestModel){
		System.out.println("secretkey=>"+validateTokenRequestModel.getSecretKey());
		String[] secretKeys = {"itiproject","eshramprojecteshramprojecteshramprojecteshramproject"};
		
		boolean find = false;
		for(int i = 0; i < secretKeys.length; i++) {
			if(validateTokenRequestModel.getSecretKey().equalsIgnoreCase(secretKeys[i])) {
				find = true;
			}
		}
		
		try {
		
		if(find) {
			
			boolean validToken = jwtService.isTokenValid(validateTokenRequestModel);
			System.out.println("validToken=>"+validToken);
			
			return ResponseEntity.ok(validToken);
		}else {
			ErrorResponse errorResponse = new ErrorResponse(new Date(), "INVALID REQUEST");
			return new ResponseEntity<ErrorResponse>(errorResponse,  HttpStatus.BAD_REQUEST);
		}
		
		}catch(Exception ex) {
			ErrorResponse errorResponse = new ErrorResponse(new Date(), "INVALID TOKEN");
			return new ResponseEntity<ErrorResponse>(errorResponse,  HttpStatus.BAD_REQUEST);
		}
		
	}

}
