package com.example.bookStoreWebApp.dto;

public class JwtResponse {
    private String token;
    private int accountTypeId;

    public JwtResponse(String token, int accountTypeId) {
        this.token = token;
        this.accountTypeId = accountTypeId;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }
}
