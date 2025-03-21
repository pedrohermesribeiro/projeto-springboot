package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.pk.OrdemItemPK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_orderItem")
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OrdemItemPK id = new OrdemItemPK();
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
		super();
	}
	
	public OrderItem(Integer quantity, Double price, Product product, Order order) {
		this.quantity = quantity;
		this.price = price;
		this.id.setOrder(order);
		this.id.setProduct(product);
	}
	
	
	@JsonIgnore
	public OrdemItemPK getId() {
		return id;
	}

	public void setId(OrdemItemPK id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	public Product getProduct() {
		return this.id.getProduct();
	}

	public void setProduct(Product product) {
		this.id.setProduct(product);
	}
	
	@JsonBackReference
	public Order getOrder() {
		return this.id.getOrder();
	}

	public void setOrder(Order order) {
		this.id.setOrder(order);
	}
	
	public Double getSubTotal() {
		return this.getPrice() * this.getQuantity();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "OrderItem [Produto=" + id.getProduct().getName() + ", quantity=" + quantity + ", price=" + price + "]";
	}


	
}
