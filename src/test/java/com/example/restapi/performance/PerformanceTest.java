package com.example.restapi.performance;

import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.restapi.service.BookService;
import com.example.restapi.service.BorrowingService;
import com.example.restapi.service.UserService;

@SpringBootTest
public class PerformanceTest {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private BorrowingService borrowingService;
    
    @Autowired
    private UserService userService;
    
    @BeforeEach
    void setup() {
        // Optional setup before each test if required
    }
    
    // Test getAllBooks() with a throughput of 50 requests per second for 10 seconds
    @Test
    @JUnitPerfTest(threads = 10, durationMs = 10000, warmUpMs = 2000)
    @JUnitPerfTestRequirement(executionsPerSec = 50, percentiles = "95:300ms")
    public void testGetAllBooksPerformance() {
        bookService.getAllBooks();
    }
    
    // Test getAllBorrowings() with 20 concurrent users, running for 5 seconds
    @Test
    @JUnitPerfTest(threads = 20, durationMs = 5000, warmUpMs = 1000)
    @JUnitPerfTestRequirement(executionsPerSec = 50, percentiles = "95:300ms")
    public void testGetAllBorrowingsPerformance() {
        borrowingService.getAllBorrowings();
    }
    
    // Test getAllUsers() with a high-load scenario of 100 users for 15 seconds
    @Test
    @JUnitPerfTest(threads = 100, durationMs = 15000, warmUpMs = 3000)
    @JUnitPerfTestRequirement(percentiles = "90:7,95:7,98:7,99:8", executionsPerSec = 1000, allowedErrorPercentage = 0.10f)
    public void testGetAllUsersPerformance() {
        userService.getAllUsers();
    }

    


}
