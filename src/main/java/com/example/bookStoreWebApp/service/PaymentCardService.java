package com.example.bookStoreWebApp.service;

import com.example.bookStoreWebApp.dto.PaymentCardDTO;
import com.example.bookStoreWebApp.model.PaymentCard;

public interface PaymentCardService {
    PaymentCard getPaymentCardByUserID(int userId);
    void saveOrUpdatePaymentCard(PaymentCardDTO paymentCardDTO, int userId);
}
