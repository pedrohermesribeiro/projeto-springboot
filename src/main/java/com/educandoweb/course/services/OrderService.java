package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.DTO.OrderDTO;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<OrderDTO> findAll() {
        List<Order> order = repository.findAll();
	    List<OrderDTO> listDto = order.stream()
		        .map(item -> {
		            OrderDTO dto = new OrderDTO(item.getId(), item.getInstante(), item.getOrderStatus(), item.getClient(),
		            		item.getOrderItems());
		            return dto;
		        })
		        .collect(Collectors.toList());
        return listDto;
    }
	
	public Optional<Order> findyById(Long id) {
		
		Optional<Order> obj = repository.findById(id);
		
		return obj;
	}
	
	public Optional<Order> update(Order<Order> order) {
		
		repository.save(order);
		
		Optional<Order> ord = repository.findById(order.getId());
		
		return ord;
	}
	
	public boolean deleteById(Long id) {
		repository.deleteById(id);
		Optional<Order> ord = repository.findById(id);
		if(ord.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	

	public String inserirOrder(Order order){
		List<User> users = userRepository.findAll();
		boolean existe = users.contains((User)order.getClient());
		System.out.println(order);
		if(!existe) {
			return "Cliente informado não cadastrado.";
		}
		List<Order> ordes = repository.findAll();
		existe = ordes.contains(order);
		if(existe) {
			return "Order já cadastrada";
		}
		repository.save(order);
		return "Order cadastrada com sucesso";
	}
}
