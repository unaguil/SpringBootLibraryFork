package com.example.restapi.service;

import com.example.restapi.model.Book;
import com.example.restapi.model.User;
import com.example.restapi.repository.BookRepository;
import com.example.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    public Book createBook(Book book) {
        System.out.println("Book Title: " + book.getTitle() + ", Author: " + book.getAuthor());
        return bookRepository.save(book);
    }
    
    public Book updateBook(Long id, Book bookDetails) throws Exception {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            return bookRepository.save(book);
        }).orElseThrow(() -> new Exception("Book not found"));
    }
    
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    public boolean borrowBook(Long bookId, Long userId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalBook.isPresent() && optionalUser.isPresent()) {
            Book book = optionalBook.get();
            if (book.getBorrower() == null) {
                book.setBorrower(optionalUser.get());
                bookRepository.save(book);
                return true;
            }
        }
        return false;
    }
    
    public boolean returnBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (book.getBorrower() != null) {
                book.setBorrower(null);
                bookRepository.save(book);
                return true;
            }
        }
        return false;
    }
}