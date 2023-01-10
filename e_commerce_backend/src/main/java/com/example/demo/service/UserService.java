package com.example.demo.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SignupResponseDto;
import com.example.demo.dto.SigninDto;
import com.example.demo.dto.SigninResponseDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.exceptions.AuthenticationFailedException;
import com.example.demo.exceptions.CustomExceptions;
import com.example.demo.model.AuthenticationToken;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Transactional
	public SignupResponseDto signup(SignupDto signupDto) {
		// check if user is already present
		// hash password
		// save the user
		// generate token
		if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
			// we alrady have user with this email
			throw new CustomExceptions("user already present");
		}
		
		// if user not present encrypt pass and store user and generate token
		
		String encryptedPassword=signupDto.getPassword();
		try {
			encryptedPassword = hashPassword(signupDto.getPassword());
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new CustomExceptions(e.getMessage());
		}
		
		User user=new User();
		user.setEmail(signupDto.getEmail());
		user.setFirstName(signupDto.getFirstName());
		user.setLastName(signupDto.getLastName());
		user.setPassword(encryptedPassword);
		
		userRepository.save(user);
		
		// now generate and save token
		
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		
		authenticationService.saveConfirmationToken(authenticationToken);
		
		return new SignupResponseDto("Success","User added");
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(password.getBytes());
		byte[] digest = messageDigest.digest();
		
		BigInteger number = new BigInteger(1, digest);
		 

        StringBuilder hexString = new StringBuilder(number.toString(16));
 
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
	}

	public SigninResponseDto signin(SigninDto signinDto) {
		// find user by email
		//hash passwrod
		//compare password
		// is password match retrive token
		
		User user = userRepository.findByEmail(signinDto.getEmail());
		if(Objects.isNull(user)) {
			throw new AuthenticationFailedException("No account with email id");
		}
		try {
			if(!user.getPassword().equals(hashPassword(signinDto.getPassword()))) {
				throw new AuthenticationFailedException("Password Incorrect");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		AuthenticationToken authenticationToken = authenticationService.getToken(user);
		
		if(Objects.isNull(authenticationToken)) {
			throw new CustomExceptions("authentication token not present");
		}
		
		return new SigninResponseDto("success",authenticationToken.getToken());
	}

}
