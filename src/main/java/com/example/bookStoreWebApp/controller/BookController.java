package com.example.bookStoreWebApp.controller;

import com.example.bookStoreWebApp.model.Books;
import com.example.bookStoreWebApp.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@CrossOrigin

public class BookController {

    @Autowired
    private BooksService booksService;

    @GetMapping
    public List<Books> getAllBooks() {
        return booksService.getAllBooks();
    }
    
    @GetMapping("/{isbn}")
    public ResponseEntity<Books> getBookByIsbn(@PathVariable String isbn) {
        Optional<Books> book = booksService.findByIsbn(isbn);
        return book.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    

    @PostMapping("/by-isbn")
    public List<Books> getBooksByIsbn(@RequestBody List<String> isbns) {
        return booksService.getBooksByIsbnList(isbns);
    }
}
