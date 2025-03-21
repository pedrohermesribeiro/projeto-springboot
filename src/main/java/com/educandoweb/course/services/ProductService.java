package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.DTO.ProductDTO;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository repositoryCategory;
	
	public List<ProductDTO> findAll() {
        List<Product> products = repository.findAll();
        return products.stream()
                       .map(item -> new ProductDTO(item.getId(),item.getName(),item.getDescription(),
                    		   item.getPrice(),item.getImgURL(),item.getCategory().getId())) 
                       .collect(Collectors.toList());
    }
	
	public Optional<Product> findyById(Long id) {
		
		Optional<Product> obj = repository.findById(id);
		
		return obj;
	}
	
	public boolean deleteById(Long id) {
		repository.deleteById(id);
		Optional<Product> prod = repository.findById(id);
		if(prod.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public String inserirProduct(Product product){
		List<Category> cat = repositoryCategory.findAll();
		boolean existe = cat.contains(product.getCategory());
		System.out.println(product);
		if(!existe) {
			return "Categoria informada não cadastrada.";
		}	
		List<Product> prod = repository.findAll();
		existe = prod.contains(product);
		if(existe) {
			return "Produto já cadastrado";
		}
		repository.save(product);
		return "Product cadastrado com sucesso";
	}
	
	public Optional<Product> update(Long id, Product prod) {
		Optional<Product> entity = repository.findById(id);
		updateProduct(entity.get(),prod);
		return entity;
	}
	
	private void updateProduct(Product entity, Product prod) {
		entity.setName(prod.getName());
		entity.setDescription(prod.getDescription());
		entity.setImgURL(prod.getImgURL());
		entity.setPrice(prod.getPrice());
		repository.save(entity);
	}
}
