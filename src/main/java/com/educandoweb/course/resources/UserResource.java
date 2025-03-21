package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value= "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>>findAll(){
		List<User> listUser = service.findAll();
		
		return ResponseEntity.ok().body(listUser);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User user = service.findyById(id);
		
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping(value = "/{id}")
	public Optional<User> update(@PathVariable Long id,@RequestBody User user){
		Optional<User> use = service.update(id, user);
		return use;
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteById(@PathVariable Long id){
		service.deleteById(id);
		return "Usuário deletado com sucesso";
	}
	
	@PostMapping
	public ResponseEntity<Optional<User>>  inserirUser(@RequestBody User user){
		Optional<User> us =  service.inserirUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(us.get().getId()).toUri();
		return ResponseEntity.created(uri).body(us);
	}
	
}
