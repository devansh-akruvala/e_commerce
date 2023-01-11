package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import com.example.demo.repository.WishlistRepository;

@Service
public class WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private ProductService productService;

	
	public Wishlist createWishlist(Wishlist wishlist) {
		return wishlistRepository.save(wishlist);
	}

	public List<ProductDto> getWishlistforUser(User user) {
	
		final List<Wishlist> wishlist = wishlistRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<ProductDto> products =  new ArrayList<ProductDto>();
		wishlist.forEach(wishlistItem -> {
			products.add(productService.productToPoroductDto(wishlistItem.getProduct()));
		});
		return products;
	}
	
	
}
