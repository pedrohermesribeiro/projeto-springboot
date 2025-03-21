package com.educandoweb.course.resources.summary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.summary.Vendas;
import com.educandoweb.course.services.summary.VendasService;

@RestController
@RequestMapping(value= "/vendas")
public class VendasResource {

	@Autowired
	private VendasService service;
	
	@GetMapping
	public ResponseEntity<List<Vendas>>findAll(){
		List<Vendas> listVendas = service.findAll();
		
		return ResponseEntity.ok().body(listVendas);
	}
	
	@GetMapping(value = "/vendas")
	public String totalVendas(){
		String resp = service.totalVendas();
		
		return resp;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Vendas>> findById(@PathVariable Long id){
		Optional<Vendas> venda = service.findyById(id);
		
		return ResponseEntity.ok().body(venda);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Optional<Vendas>> update(@PathVariable Long id){
		Vendas venda = service.findyById(id).get();
		venda.setProduct_name("Refrigerador");
		Optional<Vendas> us = service.update(venda);
		
		return ResponseEntity.ok(us);
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
	
}
