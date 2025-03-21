package com.educandoweb.course.entities.DTO;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.pk.OrdemItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderItemDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private OrdemItemPK productId; // Simplified from OrdemItemPK
    private Integer quantity;
    private Double price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrdemItemPK id, Integer quantity, Double price,Product product, Order order) {
        this.productId = id;
        this.quantity = quantity;
        this.price = price;
        this.productId.setProduct(product);
        this.productId.setOrder(order);
    }

    @JsonIgnore
    public OrdemItemPK getProductId() {
		return productId;
	}

	public void setProductId(OrdemItemPK productId) {
		this.productId = productId;
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

    public Double getSubTotal() {
        return price * quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderItemDTO other = (OrderItemDTO) obj;
        return Objects.equals(productId, other.productId);
    }

    @Override
    public String toString() {
        return "OrderItemDTO [productId=" + productId + ", quantity=" + quantity + ", price=" + price + "]";
    }
}