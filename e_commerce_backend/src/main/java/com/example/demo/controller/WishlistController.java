package com.example.demo.controller;

import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.exceptions.AuthenticationFailedException;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.WishlistService;
import com.example.demo.util.ApiResponse;

@RestController
@RequestMapping("wishlist/")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	/*
	we need to do 2 tasks
	1. save product in wishlist for user
	2. get wishlist of a user
	*/
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToWishlist(@RequestBody Product product,
			@RequestParam("token") String token) throws AuthenticationFailedException{
		
		// 1. authenticate user using token and find user
		// 2. save item in wishlist
		
		User user = authenticationService.getUser(token);
		
		Wishlist wishlist = new Wishlist(user,product);
	 
		Wishlist savedWishlist = wishlistService.createWishlist(wishlist);
		return new ResponseEntity<>(new ApiResponse(true,savedWishlist),HttpStatus.CREATED);
	}
	
	@GetMapping("/{token}")
	public ResponseEntity<List<ProductDto>> getWishlist(@PathVariable("token") String token){
		
		User user=authenticationService.getUser(token);
		List<ProductDto> products = wishlistService.getWishlistforUser(user);
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
}
