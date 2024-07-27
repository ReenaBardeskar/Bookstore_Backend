package com.example.bookStoreWebApp.service;

import com.example.bookStoreWebApp.model.Books;
import com.example.bookStoreWebApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public Books saveBook(Books book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }
    
    @Override
    public Optional<Books> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn); // Assuming findByIsbn is defined in the repository
    }
    
    @Override
    public List<Books> getBooksByIsbnList(List<String> isbns) {
        return bookRepository.findByIsbnList(isbns);
    }
}
