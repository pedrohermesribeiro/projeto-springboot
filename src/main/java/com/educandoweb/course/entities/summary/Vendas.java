package com.educandoweb.course.entities.summary;

import java.io.Serializable;
import java.util.Date;

import com.educandoweb.course.entities.OrderItem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_vendas")
public class Vendas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long order_id;
	private Date dataVenda;
	private Long product_id;
	private String product_name;
	private Integer qtd_product;
	private Double prece_unit;
	private Double totalVenda;
	
	public Vendas() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public Vendas(OrderItem item) {
		super();
		this.id = null;
		this.order_id = item.getOrder().getId();
		this.dataVenda = item.getOrder().getInstante();
		this.product_id = item.getProduct().getId();
		this.product_name = item.getProduct().getName();
		this.qtd_product = item.getQuantity();
		this.prece_unit = item.getPrice();
		this.totalVenda = item.getPrice() * item.getQuantity();
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getOrder_id() {
		return order_id;
	}



	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}



	public Date getDataVenda() {
		return dataVenda;
	}



	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}



	public Long getProduct_id() {
		return product_id;
	}



	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}



	public String getProduct_name() {
		return product_name;
	}



	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public Integer getQtd_product() {
		return qtd_product;
	}

	public void setQtd_product(Integer qtd_product) {
		this.qtd_product = qtd_product;
	}

	public Double getPrece_unit() {
		return prece_unit;
	}

	public void setPrece_unit(Double prece_unit) {
		this.prece_unit = prece_unit;
	}

	public Double getTotalVenda() {
		return totalVenda;
	}



	public void setTotalVenda(Double totalVenda) {
		this.totalVenda = totalVenda;
	}
	
	

}
