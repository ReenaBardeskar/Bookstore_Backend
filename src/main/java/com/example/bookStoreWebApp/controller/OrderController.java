package com.example.bookStoreWebApp.controller;

import com.example.bookStoreWebApp.model.Order;
import com.example.bookStoreWebApp.model.OrderItem;
import com.example.bookStoreWebApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin

public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        // Save Order
        Order order = orderRequest.getOrder();
        Order savedOrder = orderService.saveOrder(order);

        // Save Order Items
        List<OrderItem> orderItems = orderRequest.getOrderItems();
        for (OrderItem item : orderItems) {
            item.setOrderId(savedOrder.getOrderId()); // Set the order ID for each item
            orderService.saveOrderItem(item);
        }

        // Send Order Confirmation Email
        orderService.sendOrderConfirmationEmail(savedOrder, orderItems);

        return ResponseEntity.ok("Order and Order Items saved successfully, and confirmation email sent.");
    }
}
