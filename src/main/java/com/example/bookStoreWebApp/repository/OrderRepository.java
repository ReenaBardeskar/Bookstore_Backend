package com.example.bookStoreWebApp.repository;

import com.example.bookStoreWebApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Custom query methods (if any) can be defined here
}
