package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.CategoryService;

@RestController
@RequestMapping(value= "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>>findAll(){
		List<Category> listCategory = service.findAll();
		
		return ResponseEntity.ok().body(listCategory);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Category>> findById(@PathVariable Long id){
		Optional<Category> user = service.findyById(id);
		
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Optional<Category>> update(@PathVariable Long id){
		Category category = service.findyById(id).get();
		category.setName("Utilitários");
		Optional<Category> cat = service.update(category);
		
		return ResponseEntity.ok(cat);
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteById(@PathVariable Long id){
		boolean resp = service.deleteById(id);
		if(resp) {
			return "Usuário deletado com sucesso";
		}else {
			return "Usuário não deletado";
		}
	}
	
	@PostMapping
	public ResponseEntity<Category>  inserirCategory(@RequestBody Category category){
		Category cat =  service.inserirCategory(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).body(cat);
	}
	
}
