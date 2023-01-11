package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemDto;
import com.example.demo.exceptions.CustomExceptions;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductService productService;

	public void addToCart(AddToCartDto addToCartDto, User user) {
		Product product =  productService.findById(addToCartDto.getProductId());
		
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setCreatedDate(new Date());
		cart.setQuantity(addToCartDto.getQuantity());
		
		cartRepository.save(cart);
		
		
	}

	public CartDto getCartItems(User user) {
		List<Cart> cartItems=cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDto> cartItemDtos=new ArrayList<CartItemDto>();
		double totalCost=0;
		for(Cart item:cartItems) {
			cartItemDtos.add(new CartItemDto(item));
			totalCost+=(item.getQuantity() * item.getProduct().getProductPrice());
		}
		CartDto cartDto = new CartDto();
		cartDto.setTotalCost(totalCost);
		cartDto.setCartItem(cartItemDtos);
		return cartDto;
	}

	public void deleteItemFromCart(Integer cartItemId, User user) {
		
		Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
		if(optionalCart.isEmpty()) {
			throw new CustomExceptions("cart item id is invalid");
		}
		
		Cart cart = optionalCart.get();
		if(cart.getUser()!=user) {
			throw new CustomExceptions("Cart doesnot belong to user");
		}
		cartRepository.delete(cart);
	}
	
	
	
}
