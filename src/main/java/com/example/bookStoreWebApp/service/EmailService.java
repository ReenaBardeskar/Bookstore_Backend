package com.example.bookStoreWebApp.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
