package com.example.bookStoreWebApp.service;

import com.example.bookStoreWebApp.model.Order;
import com.example.bookStoreWebApp.model.OrderItem;
import com.example.bookStoreWebApp.model.Users;
import com.example.bookStoreWebApp.repository.OrderRepository;
import com.example.bookStoreWebApp.repository.UserRepository;
import com.example.bookStoreWebApp.repository.BookRepository;
import com.example.bookStoreWebApp.repository.OrderItemRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    
    @Autowired
    private EmailService emailService;

    @Autowired
    private BookRepository booksRepository;
    
    @Autowired
    private UserRepository usersRepository;
    
    
    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
    
    
    public void sendOrderConfirmationEmail(Order order, List<OrderItem> orderItems) {
        // Retrieve the user by UserID
        Users user = usersRepository.findById(order.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

     // Retrieve book titles for the order items
        List<String> bookTitles = orderItems.stream()
            .map(item -> booksRepository.findById(item.getBookId()))
            .flatMap(optionalBook -> optionalBook.map(book -> Stream.of(book.getTitle())).orElseGet(Stream::empty))
            .collect(Collectors.toList());

        // Build the email content
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Thank you for your order! Here are the details:\n\n");
        emailContent.append("Order ID: ").append(order.getOrderId()).append("\n");
        emailContent.append("Total Amount: $").append(String.format("%.2f", order.getTotalAmount())).append("\n\n");

        emailContent.append("Books Ordered:\n");
        for (int i = 0; i < orderItems.size(); i++) {
            emailContent.append(bookTitles.get(i))
                .append(" - Quantity: ").append(orderItems.get(i).getQuantity())
                .append("\n");
        }

        // Send email
        emailService.sendEmail(user.getEmail(), "Order Confirmation", emailContent.toString());
    }
}
