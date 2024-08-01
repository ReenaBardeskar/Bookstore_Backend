package com.example.bookStoreWebApp.service;

import java.util.List;

import com.example.bookStoreWebApp.model.Order;
import com.example.bookStoreWebApp.model.OrderItem;

public interface OrderService {
    Order saveOrder(Order order);
    void saveOrderItem(OrderItem orderItem);

    void sendOrderConfirmationEmail(Order order, List<OrderItem> orderItems);
}
