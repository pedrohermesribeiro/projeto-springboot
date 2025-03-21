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

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.DTO.OrderDTO;
import com.educandoweb.course.entities.DTO.OrderItemDTO;
import com.educandoweb.course.services.OrderService;

@RestController
@RequestMapping(value= "/orders")
public class OrderResource {


	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
	    List<OrderDTO> list = service.findAll();
	    return ResponseEntity.ok().body(list);
	}

	private OrderDTO convertToDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setInstante(order.getInstante());
		orderDTO.setOrderStatus(order.getOrderStatus());
		orderDTO.setClient(order.getClient());
		
		return orderDTO;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Order>> findById(@PathVariable Long id){
		Optional<Order> order = service.findyById(id);
		
		return ResponseEntity.ok().body(order);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Optional<Order>> update(@PathVariable Long id){
		Order order = service.findyById(id).get();
		order.setInstante(null);
		//order.setId_user(null);
		Optional<Order> ord = service.update(order);
		
		return ResponseEntity.ok(ord);
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
	public String  inserirOrder(@RequestBody Order order){
		String resp = service.inserirOrder(order);
		return resp;
	}
}
