package com.example.bookStoreWebApp.dto;

import com.example.bookStoreWebApp.model.ShippingAddress;

public class ShippingAddressDTO {
    private int addressId;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private int userId;

    // Default constructor
    public ShippingAddressDTO() {
    }

    // Constructor to initialize from ShippingAddress entity
    public ShippingAddressDTO(ShippingAddress address) {
        this.addressId = address.getAddressID();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.zipCode = address.getZipCode();
        this.userId = address.getUserId();
    }

    // Getters and setters
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
