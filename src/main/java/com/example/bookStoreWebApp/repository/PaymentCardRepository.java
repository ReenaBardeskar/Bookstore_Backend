package com.example.bookStoreWebApp.repository;

import com.example.bookStoreWebApp.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Integer> {
    Optional<PaymentCard> findByUserID(int userId);
}
