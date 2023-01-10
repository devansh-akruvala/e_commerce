package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
