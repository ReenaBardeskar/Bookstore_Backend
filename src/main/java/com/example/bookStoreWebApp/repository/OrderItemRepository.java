package com.example.bookStoreWebApp.repository;

import com.example.bookStoreWebApp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    // Custom query methods (if any) can be defined here
}
