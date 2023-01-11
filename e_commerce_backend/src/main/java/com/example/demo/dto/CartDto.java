package com.example.demo.dto;

import java.util.List;

public class CartDto {
	private List<CartItemDto> cartItem;
	private Double totalCost;
	
	public List<CartItemDto> getCartItem() {
		return cartItem;
	}
	public void setCartItem(List<CartItemDto> cartItem) {
		this.cartItem = cartItem;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	
}
