package com.educandoweb.course.services.summary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.summary.Vendas;
import com.educandoweb.course.repositories.summary.VendasRepository;

@Service
public class VendasService {

	@Autowired
	private VendasRepository repository;
	
	
	
	public VendasService() {

		
	}
	
	public List<Vendas> findAll(){
		return repository.findAll();
	}
	
	public String totalVendas(){
		List<Vendas> item = repository.findAll();
		Double total = 0.0;
		Integer qtde = 0;
		for(Vendas venda : item) {
			total = total + venda.getTotalVenda();
			qtde = qtde + venda.getQtd_product();
		}
		
		
		return "Quantidade de produtos " + qtde + " total das vendas " + total;
	}
	
	public Optional<Vendas> findyById(Long id) {
		
		Optional<Vendas> obj = repository.findById(id);
		
		return obj;
	}
	
	public Optional<Vendas> update(Vendas vendas) {
		
		repository.save(vendas);
		
		Optional<Vendas> venda = repository.findById(vendas.getId());
		
		return venda;
	}
	
	public boolean deleteById(Long id) {
		repository.deleteById(id);
		Optional<Vendas> venda = repository.findById(id);
		if(venda.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
}
