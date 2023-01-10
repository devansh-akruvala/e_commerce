package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.util.ApiResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
		// every product has category id
		// so we chage if its valid or not 
		Optional<Category> optionalCategory= categoryService.getCategoryById(productDto.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false,"Invalid Category Id"),HttpStatus.BAD_REQUEST);
		}
		Product product = productService.createProduct(productDto,optionalCategory.get());
		
		return new ResponseEntity<>(new ApiResponse(true, product),HttpStatus.CREATED);
	}
	
}
