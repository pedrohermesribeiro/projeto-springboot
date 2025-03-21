package com.educandoweb.course.resources;

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

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.DTO.ProductDTO;
import com.educandoweb.course.services.ProductService;

@RestController
@RequestMapping(value= "/products")
public class ProductResource {


	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>>findAll(){
		List<ProductDTO> listProduct = service.findAll();
		return ResponseEntity.ok().body(listProduct);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Product>> findById(@PathVariable Long id){
		Optional<Product> prod = service.findyById(id);
		
		return ResponseEntity.ok().body(prod);
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
	public String  inserirProduct(@RequestBody Product product){
		String resp = service.inserirProduct(product);
		return resp;
	}
	
	@PutMapping(value = "/{id}")
	public Optional<Product> update(@PathVariable Long id,@RequestBody Product prod){
		Optional<Product> product = service.update(id,prod); 
		return product;
	}
}
