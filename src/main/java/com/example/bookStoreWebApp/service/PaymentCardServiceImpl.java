package com.example.bookStoreWebApp.service;

import com.example.bookStoreWebApp.dto.PaymentCardDTO;
import com.example.bookStoreWebApp.model.PaymentCard;
import com.example.bookStoreWebApp.repository.PaymentCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCardServiceImpl implements PaymentCardService {

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @Override
    public PaymentCard getPaymentCardByUserID(int userId) {
        Optional<PaymentCard> optionalCard = paymentCardRepository.findByUserID(userId);
        return optionalCard.orElse(null);
    }

    @Override
    public void saveOrUpdatePaymentCard(PaymentCardDTO paymentCardDTO, int userId) {
        Optional<PaymentCard> optionalCard = paymentCardRepository.findByUserID(userId);
        PaymentCard paymentCard;

        if (optionalCard.isPresent()) {
            paymentCard = optionalCard.get();
        } else {
            paymentCard = new PaymentCard();
            paymentCard.setUserID(userId);
        }

        paymentCard.setCardNumber(paymentCardDTO.getCardNumber());
        paymentCard.setExpiryDate(paymentCardDTO.getExpiryDate());
        paymentCard.setCardHolder(paymentCardDTO.getCardHolder());
        paymentCard.setCardType(paymentCardDTO.getCardType());

        paymentCardRepository.save(paymentCard);
    }
}
