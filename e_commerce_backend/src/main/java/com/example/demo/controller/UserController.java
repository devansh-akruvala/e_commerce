package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dto.SigninDto;
import com.example.demo.dto.SigninResponseDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.SignupResponseDto;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public SignupResponseDto signup(@RequestBody SignupDto signupDto) {
		return userService.signup(signupDto);
	}
	
	@PostMapping("/signin")
	public SigninResponseDto signin(@RequestBody SigninDto signinDto) {
		return userService.signin(signinDto);
	}
}
