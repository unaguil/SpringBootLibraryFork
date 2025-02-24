package com.example.restapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrowings")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public Borrowing() {}

    public Borrowing(User user, Book book) {
        this.user = user;
        this.book = book;
        this.borrowDate = LocalDateTime.now();
        this.returnDate = null;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Book getBook() { return book; }
    public LocalDateTime getBorrowDate() { return borrowDate; }
    public LocalDateTime getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
}
