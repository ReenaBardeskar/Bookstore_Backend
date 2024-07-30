package com.example.bookStoreWebApp.repository;

import com.example.bookStoreWebApp.model.ShippingAddress;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
	Optional<ShippingAddress> findByUserId(int userId);
}
