package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}
		
	public List<Category> listCategory(){
		return categoryRepository.findAll();
	}

	public Category editCategory(int id,Category category) {
		Category updateCategory = new Category();
		updateCategory.setCategoryName(category.getCategoryName());
		updateCategory.setCategoryId(id);
		updateCategory.setCategoryDescription(category.getCategoryDescription());
		updateCategory.setCategoryImageUrl(category.getCategoryImageUrl());
		
		return categoryRepository.save(updateCategory);
	}
	
	public Optional<Category> getCategoryById(int categoryId) {
		return categoryRepository.findById(categoryId);
	}
}

