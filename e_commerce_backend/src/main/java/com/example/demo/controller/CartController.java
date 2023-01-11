package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.CartDto;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.CartService;
import com.example.demo.util.ApiResponse;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
			@RequestParam("token") String token){
		
		User user = authenticationService.getUser(token);
		
		cartService.addToCart(addToCartDto,user);
		
		return new ResponseEntity<>(new ApiResponse(true, "Added in Cart"),HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
		User user = authenticationService.getUser(token);
		CartDto cartDto = cartService.getCartItems(user);
		return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	}
}
