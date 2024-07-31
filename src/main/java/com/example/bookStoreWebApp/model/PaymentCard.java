package com.example.bookStoreWebApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_cards")
public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentID;

    @Column(name = "Card_Number", length = 16)
    private String cardNumber;

    @Column(name = "Expiry_Date", length = 7)
    private String expiryDate;

    @Column(name = "Card_Holder", length = 100)
    private String cardHolder;

    @Column(name = "Card_Type", length = 20)
    private String cardType;

    @Column(name = "UserID")
    private int userID;
    
    
    // Getters and setters


	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

    
}
