package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		String status = "NOT_FOUND_404";
		StandardError err = new StandardError(Instant.now(),status,error,e.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(404).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		String error = "Database error";
		String status = "BAD_REQUEST";
		StandardError err = new StandardError(Instant.now(),status,error,e.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(400).body(err);
	}
		
	
}
