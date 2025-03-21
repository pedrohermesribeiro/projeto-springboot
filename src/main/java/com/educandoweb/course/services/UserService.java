package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findyById(Long id) {
		
		Optional<User> obj = repository.findById(id);
		
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	
	public Optional<User> update(Long id, User user) {
		Optional<User> entity = repository.findById(id);
		if(entity.isEmpty()) {
			throw new ResourceNotFoundException(id);
		}
		updateUser(entity.get(),user);
		return entity;
	}
	
	private void updateUser(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		entity.setPhone(user.getPhone());
		repository.save(entity);
	}

	public void deleteById(Long id) {
		User user = findyById(id);
		if(user == null) {
			throw new ResourceNotFoundException(id);
		}
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Optional<User> inserirUser(User user){
		List<User> us = repository.findAll();
		boolean existe = us.contains(user);
		if(existe) {
			System.out.println("Usuário já cadastrado");
			return null;
		}
		return  Optional.ofNullable(repository.save(user));
		
	}
}
