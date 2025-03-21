package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.DTO.OrderItemDTO;
//import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	@Autowired
	private ProductRepository prodRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<OrderItemDTO> findAll() {
        List<OrderItem> orderItem = repository.findAll();
        return orderItem.stream()
                       .map(item -> new OrderItemDTO(item.getId(),item.getQuantity(),item.getPrice(),
                    		   item.getProduct(),item.getOrder())) 
                       .collect(Collectors.toList());
    }
	
	public Optional<OrderItem> findyById(Long id) {
		
		Optional<OrderItem> obj = repository.findById(id);
		
		return obj;
	}
	
	public OrderItem update(OrderItem orderItem) {
		
		repository.save(orderItem);
		
		OrderItem item = orderItem;
		
		return item;
	}
	
	public boolean deleteById(Long id) {
		repository.deleteById(id);
		Optional<OrderItem> item = repository.findById(id);
		if(item.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public String inserirOrderItem(OrderItem orderItem){
		List<Product> prods = prodRepository.findAll();
		boolean existe = prods.contains(orderItem.getProduct());
		if(!existe) {
			System.out.println(orderItem);
			return "Produto informado não cadastrado.";
		}
		List<Order> ords = orderRepository.findAll();
		existe = ords.contains( orderItem.getOrder());
		if(!existe) {
			return "Order informada não cadastrada.";
		}
		List<OrderItem> itens = repository.findAll();
		existe = itens.contains(orderItem);
		if(existe) {
			return "OrderItem já cadastrada";
		}
		repository.save(orderItem);
		return "OrderItem cadastrada com sucesso";
	}
}
