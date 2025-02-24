package com.example.restapi.controller;

import com.example.restapi.model.Book;
import com.example.restapi.model.User;
import com.example.restapi.service.BorrowingService;
import com.example.restapi.service.UserService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrows")
@Hidden  // This will hide the entire BorrowingController from Swagger
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @Autowired
    private UserService userService;

    @PostMapping("/{bookId}/borrows")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @RequestBody User user) {
        return borrowingService.borrowBook(bookId, user) ?
            ResponseEntity.ok("Book borrowed successfully") :
            ResponseEntity.badRequest().body("Book is already borrowed");
    }

    @PostMapping("/{bookId}/returns")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        return borrowingService.returnBook(bookId) ?
            ResponseEntity.ok("Book returned successfully") :
            ResponseEntity.badRequest().body("Book was not borrowed");
    }

    /* 
    @GetMapping("/users/{username}/borrowed-books")
    public List<Book> getBorrowedBooksByUser(@PathVariable String username) {
        Optional<User> userOptional = userService.findByUsername(username); // Already returns Optional<User>

        if (userOptional.isEmpty()) {
            return List.of(); // Return empty list if user is not found
        }

        return borrowingService.getBorrowedBooksByUser(username);
    } */


    @GetMapping("/users/{username}/borrowed-books")
    public ResponseEntity<List<Map<String, Object>>> getBorrowedBooksByUser(@PathVariable String username) {
        List<Map<String, Object>> borrowedBooks = borrowingService.getBorrowedBooksByUser(username);
        return ResponseEntity.ok(borrowedBooks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long bookId, @PathVariable Long userId) {
        borrowingService.deleteByBookUserId(bookId, userId);
        return ResponseEntity.noContent().build();
    }    
}