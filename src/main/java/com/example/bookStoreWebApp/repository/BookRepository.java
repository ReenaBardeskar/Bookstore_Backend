package com.example.bookStoreWebApp.repository;


import com.example.bookStoreWebApp.model.Books;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Integer> {
    Optional<Books> findByIsbn(String isbn); // New method

}
