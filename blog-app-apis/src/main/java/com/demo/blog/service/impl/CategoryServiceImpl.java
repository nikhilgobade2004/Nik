package com.demo.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.blog.entities.Category;
import com.demo.blog.exceptions.ResourceNotFoundException;
import com.demo.blog.payloads.CategoryDto;
import com.demo.blog.repository.CategoryRepository;
import com.demo.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//create
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category  cat=this.modelMapper.map(categoryDto, Category.class);
		Category saveCategory=this.categoryRepository.save(cat);
		return this.modelMapper.map(saveCategory, CategoryDto.class);
	}

	//update
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCat=this.categoryRepository.save(cat);
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	//delete
	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepository.findById(categoryId)
		.orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepository.delete(cat);
		
	}

	//get
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepository.findById(categoryId)
		.orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	//getAll
	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories=this.categoryRepository.findAll();
		List<CategoryDto> categoriesDto= categories.stream()
				.map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return categoriesDto;
	}

}
