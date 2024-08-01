package com.example.bookStoreWebApp.controller;

import com.example.bookStoreWebApp.model.Order;
import com.example.bookStoreWebApp.model.OrderItem;

import java.util.List;

public class OrderRequest {
    private Order order;
    private List<OrderItem> orderItems;

    
    // Getters and Setters
	
    
    public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
