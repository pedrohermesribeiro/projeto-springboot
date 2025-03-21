package com.educandoweb.course.entities.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date instante;
	private OrderStatus orderStatus;
	private User client;
	private PaymentDTO payment;
	private List<OrderItem> orderItems = new ArrayList<>();
	private Double total;

	public OrderDTO() {
	}

	public OrderDTO(Long id, Date instante, OrderStatus orderStatus, User user,List<OrderItem> orderItem) {
		this.id = id;
		this.instante = instante;
		this.orderStatus = orderStatus;
		this.client = user;
		this.orderItems = orderItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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
		OrderDTO other = (OrderDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", instante=" + instante + ", orderStatus=" + orderStatus + ", client=" + client
				+ ", payment=" + payment + ", orderItems=" + orderItems + ", total=" + total + "]";
	}

	
	
}