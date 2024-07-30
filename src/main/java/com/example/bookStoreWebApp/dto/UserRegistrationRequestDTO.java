package com.example.bookStoreWebApp.dto;


public class UserRegistrationRequestDTO {
    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private boolean subscribeToPromo;
    private ShippingAddressDTO shippingAddress; // Add this line

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isSubscribeToPromo() {
        return subscribeToPromo;
    }

    public void setSubscribeToPromo(boolean subscribeToPromo) {
        this.subscribeToPromo = subscribeToPromo;
    }

    public ShippingAddressDTO getShippingAddress() {
        return shippingAddress; // Add this method
    }

    public void setShippingAddress(ShippingAddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress; // Add this method
    }
}
