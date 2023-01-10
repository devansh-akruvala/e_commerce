package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

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
	
	
	public List<ProductDto> getAllProducts(){
		List<Product> products=productRepository.findAll();
		List<ProductDto> allProducts=new ArrayList<>();
		products.forEach(product -> {
			allProducts.add(productToPoroductDto(product));
		});
		return allProducts;
	}
	
	
	
	
	
	
	
	
	private ProductDto productToPoroductDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setProductName(product.getProductName());
		productDto.setProductDescription(		product.getProductDescription());
		productDto.setProductImageUrl(		product.getProductImageUrl());
		productDto.setProductPrice(		product.getProductPrice());
		productDto.setCategoryId(product.getCategory().getCategoryId());
		return productDto;
	}
	

	
	
	
	
	
	
	//	private Product productDtoToProduct(ProductDto productDto) {
//		Product product = new Product();
//		product.setProductName(productDto.getProductName());
//		product.setProductDescription(productDto.getProductDescription());
//		product.setProductImageUrl(productDto.getProductImageUrl());
//		product.setProductPrice(productDto.getProductPrice());
//		product.setCategory(category);
//		
//	}
}
