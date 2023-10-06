package com.authserver.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.authserver.model.TokenRequestModel;
import com.authserver.model.ValidateTokenRequestModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	
	public boolean isTokenValid(ValidateTokenRequestModel validateTokenRequestModel) {
		
		boolean validToken = false;
		
		String[] secretKeys = {"itiproject","eshramprojecteshramprojecteshramprojecteshramproject"};
		
		boolean find = false;
		for(int i = 0; i < secretKeys.length; i++) {
			if(validateTokenRequestModel.getSecretKey().equalsIgnoreCase(secretKeys[i])) {
				find = true;
			}else {
				find = false;
			}
		}
		
		if(find) {
			boolean expired = isTokenExpired(validateTokenRequestModel);
			if(expired) {
				validToken = false;
			}else {
				validToken = true;
			}
		}else {
			validToken = false;
		}
		return  validToken;
	}
	
	
	private boolean isTokenExpired(ValidateTokenRequestModel validateTokenRequestModel) {
		// TODO Auto-generated method stub
		return extractExpiration(validateTokenRequestModel).before(new Date());
	}

	private Date extractExpiration(ValidateTokenRequestModel validateTokenRequestModel) {
		// TODO Auto-generated method stub
		return extractClaim(validateTokenRequestModel, Claims::getExpiration);
	}
	
	public <T> T extractClaim(ValidateTokenRequestModel validateTokenRequestModel, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(validateTokenRequestModel);
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(ValidateTokenRequestModel validateTokenRequestModel) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey(validateTokenRequestModel.getSecretKey()))
				.build()
				.parseClaimsJws(validateTokenRequestModel.getJwtToken())
				.getBody();
	}
	public String generateToken(TokenRequestModel tokenRequestModel) {
		return Jwts
				.builder()
				.setClaims(tokenRequestModel.getData())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(tokenRequestModel.getSecretKey()), SignatureAlgorithm.HS256)
				.compact();
	}
	private Key getSignInKey(String secretKey) {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
