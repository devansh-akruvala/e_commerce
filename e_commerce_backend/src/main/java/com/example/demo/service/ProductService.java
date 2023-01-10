package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product createProduct(ProductDto productDto,Category category) {
		Product product = new Product();
		product.setProductName(productDto.getProductName());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductImageUrl(productDto.getProductImageUrl());
		product.setProductPrice(productDto.getProductPrice());
		product.setCategory(category);
		
		return productRepository.save(product);
	}
	
	
}
