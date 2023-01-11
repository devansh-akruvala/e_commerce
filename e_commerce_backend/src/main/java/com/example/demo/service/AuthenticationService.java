package com.example.demo.service;

import java.util.Objects;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AuthenticationFailedException;
import com.example.demo.model.AuthenticationToken;
import com.example.demo.model.User;
import com.example.demo.repository.TokenRepository;

@Service
public class AuthenticationService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
	}

	public AuthenticationToken getToken(User user) {
		return tokenRepository.findByUser(user);
	}

	public User getUser(String token) throws AuthenticationFailedException {
		if(Objects.isNull(token)) {
			throw new AuthenticationFailedException("token not present");
		}
		
		AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
		if(Objects.isNull(authenticationToken)) {
			throw new AuthenticationFailedException("Invalid User");
		}
		
		return authenticationToken.getUser();
	}
	
	
}
