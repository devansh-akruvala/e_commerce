package com.example.demo.service;

import java.util.List;

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
}
