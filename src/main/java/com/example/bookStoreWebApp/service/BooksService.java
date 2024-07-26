package com.example.bookStoreWebApp.service;

import com.example.bookStoreWebApp.model.Books;
import java.util.List;
import java.util.Optional;

public interface BooksService {
    List<Books> getAllBooks();
    Optional<Books> findByIsbn(String isbn); 
}
