package com.example.bookStoreWebApp.repository;


import com.example.bookStoreWebApp.model.Books;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Books, Integer> {
    Optional<Books> findByIsbn(String isbn); // New method
    
    @Query("SELECT b FROM Books b WHERE b.isbn IN :isbns")
    List<Books> findByIsbnList(@Param("isbns") List<String> isbns);
    
    @Query("SELECT b FROM Books b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(b.category) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
     List<Books> searchBooks(@Param("searchTerm") String searchTerm);
    

}
