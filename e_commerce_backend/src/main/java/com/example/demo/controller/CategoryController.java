package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.util.ApiResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		Category savedcategory=categoryService.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true, savedcategory),HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Category>> listCategory(){
		List<Category> categories = categoryService.listCategory();
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(
			@PathVariable("categoryId") Integer id,
			@RequestBody Category category) {
		Category editedCategory = categoryService.editCategory(id, category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, editedCategory),HttpStatus.OK);
	}
	
}
