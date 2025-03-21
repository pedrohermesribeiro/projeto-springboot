package com.educandoweb.course.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Payment;
import com.educandoweb.course.entities.Product;
//import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.entities.summary.Vendas;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
//import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.repositories.summary.VendasRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private VendasRepository vendasRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		User u3 = new User(null, "Raimundo Fonseca", "alex@gmail.com", "977777777", "123456"); 
		User u4 = new User(null, "Manoel Gonzales", "alex@gmail.com", "977777777", "123456"); 
		
		userRepository.saveAll(Arrays.asList(u1,u2,u3,u4));
		
		Category cat1 = new Category(null, "Eletrônicos"); 
		Category cat2 = new Category(null, "Importados"); 
		Category cat3 = new Category(null, "Equipamentos"); 
		Category cat4 = new Category(null, "Escritório"); 
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4));
		
		Product prod1 = new Product(null, "Iphone 500", "telefone celular", 3500.0, "www.google.com",cat1); 
		Product prod2 = new Product(null, "TV Led", "televisor de led", 4500.0, "www.google.com",cat2); 
		Product prod3 = new Product(null, "Notebook", "computador de mesa", 3800.0, "www.google.com",cat1); 
		Product prod4 = new Product(null, "Fone de ouvido", "fone de ouvido Philps", 500.0, "www.google.com",cat2); 
		
		productRepository.saveAll(Arrays.asList(prod1,prod2,prod3,prod4));
		
		
		String str = "10/02/2002";
		Date d1 = sdf.parse(str);
		Order ord1 = new Order(null,d1,OrderStatus.WAITING_PAYMENT , u1);
		Order ord2 = new Order(null,d1,OrderStatus.PAID, u2);
		Order ord3 = new Order(null,d1,OrderStatus.SHIPPED, u1);
		Order ord4 = new Order(null,d1,OrderStatus.DELIVERED, u2);
		Order ord5 = new Order(null,d1,OrderStatus.DELIVERED, u3);
		Order ord6 = new Order(null,d1,OrderStatus.DELIVERED, u4);
		
		orderRepository.saveAll(Arrays.asList(ord1,ord2,ord3,ord4,ord5,ord6));
		
		OrderItem item1 = new OrderItem(5,3500.0,prod1,ord1);
		OrderItem item2 = new OrderItem(3,4000.0,prod2,ord2);
		OrderItem item3 = new OrderItem(8,3000.0, prod3,ord4);
		OrderItem item4 = new OrderItem(2,2000.0, prod2,ord1);
		OrderItem item5 = new OrderItem(2,2000.0, prod4,ord3);
		OrderItem item6 = new OrderItem(2,2000.0, prod1,ord2);
		OrderItem item7 = new OrderItem(2,2000.0, prod3,ord2);
		OrderItem item8 = new OrderItem(2,2000.0, prod4,ord5);
		OrderItem item9 = new OrderItem(2,2000.0, prod1,ord6);
		
		orderItemRepository.saveAll(Arrays.asList(item1,item2,item3,item4,item5,item6,item7,item8,item9));
		
		
		Vendas venda1 = new Vendas(item1);
		Vendas venda2 = new Vendas(item2);
		Vendas venda3 = new Vendas(item3);
		Vendas venda4 = new Vendas(item4);
		Vendas venda5 = new Vendas(item5);
		Vendas venda6 = new Vendas(item6);
		Vendas venda7 = new Vendas(item7);
		Vendas venda8 = new Vendas(item8);
		Vendas venda9 = new Vendas(item9);

		vendasRepository.saveAll(Arrays.asList(venda1,venda2,venda3,venda4,venda5,venda6,venda7,venda8,venda9));
		
		
		Payment pay1 = new Payment(null,d1,ord2);
		
		ord2.setPayment(pay1);
		
		orderRepository.save(ord2);
		
	
	}
	
	

}
