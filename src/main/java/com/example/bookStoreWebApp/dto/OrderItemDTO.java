package com.example.bookStoreWebApp.dto;

import java.math.BigDecimal;

public class OrderItemDTO {
    private Integer bookId;
    private Integer quantity;
    private BigDecimal price;

    // Getters and Setters
	
    public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
