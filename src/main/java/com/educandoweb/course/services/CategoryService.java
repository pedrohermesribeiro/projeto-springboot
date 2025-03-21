package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Optional<Category> findyById(Long id) {
		
		Optional<Category> obj = repository.findById(id);
		
		return obj;
	}
	
	public Optional<Category> update(Category category) {
		
		repository.save(category);
		
		Optional<Category> cat = repository.findById(category.getId());
		
		return cat;
	}
	
	public boolean deleteById(Long id) {
		repository.deleteById(id);
		Optional<Category> cat = repository.findById(id);
		if(cat.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public Category inserirCategory(Category category){
		List<Category> cats = repository.findAll();
		boolean existe = cats.contains(category);
		if(existe) {
			System.out.println("Category já cadastrada");
			return null;
		}
		return  repository.save(category);
		
	}
}
