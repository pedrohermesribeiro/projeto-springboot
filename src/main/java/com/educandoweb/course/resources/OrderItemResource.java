package com.educandoweb.course.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.DTO.OrderItemDTO;
import com.educandoweb.course.entities.DTO.ProductDTO;
import com.educandoweb.course.services.OrderItemService;

@RestController
@RequestMapping(value= "/orderitems")
public class OrderItemResource {

	@Autowired
	private OrderItemService service;
	
	@GetMapping
	public ResponseEntity<List<OrderItemDTO>>findAll(){
		List<OrderItemDTO> listOrderItem = service.findAll();
		return ResponseEntity.ok().body(listOrderItem);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<OrderItem>> findById(@PathVariable Long id){
		Optional<OrderItem> item = service.findyById(id);
		
		return ResponseEntity.ok().body(item);
	}
	
	@PutMapping(value = "/{id}")
	public OrderItem update(@PathVariable Long id){
		OrderItem item = service.findyById(id).get();
		item.setPrice(5000.0);
		item.setQuantity(10);
		OrderItem itens = service.update(item);
		
		return itens;
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
	public String  inserirOrderItem(@RequestBody OrderItem orderItem){
		String resp = service.inserirOrderItem(orderItem);
		return resp;
	}
}
