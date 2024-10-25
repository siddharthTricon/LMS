//package com.example.Library.controller;
//
//import com.example.Library.model.Book;
//import com.example.Library.model.Student;
//import com.example.Library.service.BookService;
//import com.example.Library.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/student")
//public class StudentController {
//
//    @Autowired
//    private BookService bookService; // Service for managing books
//    @Autowired
//    private StudentService studentService; // Service for managing students
//
//    @GetMapping("/searchBook")
//    public ResponseEntity<List<Book>> searchBook(@RequestParam String authorName, @RequestParam String bookName) {
//        // Logic to search for books by author name and book name
//        List<Book> books = bookService.findByAuthorAndTitle(authorName, bookName);
//
//        return ResponseEntity.ok(books);
//    }
//
//    @PostMapping("/takeBook")
//    public ResponseEntity<String> takeBook(@RequestParam Long studentId, @RequestParam Long bookId) {
//        // Logic to issue the book to the student
//
//        boolean isIssued = bookService.issueBook(studentId, bookId);
//        if (isIssued) {
//            return ResponseEntity.ok("Book issued successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("Book could not be issued.");
//        }
//    }
//
//    @PostMapping("/returnBook")
//    public ResponseEntity<String> returnBook(@RequestParam Long studentId, @RequestParam Long bookId) {
//        // Logic to return the book from the student
//        boolean isReturned = bookService.returnBook(studentId, bookId);
//        if (isReturned) {
//            return ResponseEntity.ok("Book returned successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("Book could not be returned.");
//        }
//    }
//
//    @PostMapping("/payFine")
//    public ResponseEntity<String> payFine(@RequestParam Long studentId, @RequestParam double amountPaid) {
//        // Logic to pay a fine
//        Student student = studentService.findStudentById(studentId);
//        if (student == null) {
//            return ResponseEntity.badRequest().body("Student not found");
//        }
//
//        double currentFine = student.getFineAmount();
//        if (amountPaid >= currentFine) {
//            student.setFineAmount(0);
//            studentService.saveStudent(student);
//            return ResponseEntity.ok("Fine paid in full.");
//        } else {
//            student.setFineAmount(currentFine - amountPaid);
//            studentService.saveStudent(student);
//            return ResponseEntity.ok("Partial fine payment accepted. Remaining fine: " + student.getFineAmount());
//        }
//    }
//
//}


package com.example.Library.controller;

import com.example.Library.exception.ResourceNotFoundException;
import com.example.Library.model.Book;
import com.example.Library.model.Student;
import com.example.Library.response.ApiResponse;
import com.example.Library.service.BookService;
import com.example.Library.service.StudentService;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/student")
@Validated
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/searchBook")
    public ResponseEntity<List<Book>> searchBook(@RequestParam String authorName, @RequestParam String bookName) {
        logger.info("Searching for books by author: {} and title: {}", authorName, bookName);
        List<Book> books = bookService.findByAuthorAndTitle(authorName, bookName);

        if (books.isEmpty()) {
            logger.warn("No books found for author: {} and title: {}", authorName, bookName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(books);
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping("/takeBook")
    public ResponseEntity<ApiResponse> takeBook(@RequestParam @Min(1) Long studentId, @RequestParam @Min(1) Long bookId) {
        logger.info("Issuing book ID: {} to student ID: {}", bookId, studentId);
        boolean isIssued = bookService.issueBook(studentId, bookId);
        if (isIssued) {
            logger.info("Book issued successfully.");
            return ResponseEntity.ok(new ApiResponse("Book issued successfully.", true));
        } else {
            logger.warn("Failed to issue book ID: {} to student ID: {}", bookId, studentId);
            return ResponseEntity.badRequest().body(new ApiResponse("Book could not be issued.", false));
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<ApiResponse> returnBook(@RequestParam @Min(1) Long studentId, @RequestParam @Min(1) Long bookId) {
        logger.info("Returning book ID: {} from student ID: {}", bookId, studentId);
        boolean isReturned = bookService.returnBook(studentId, bookId);
        if (isReturned) {
            logger.info("Book returned successfully.");
            return ResponseEntity.ok(new ApiResponse("Book returned successfully.", true));
        } else {
            logger.warn("Failed to return book ID: {} from student ID: {}", bookId, studentId);
            return ResponseEntity.badRequest().body(new ApiResponse("Book could not be returned.", false));
        }
    }

    @PostMapping("/payFine")
    public ResponseEntity<ApiResponse> payFine(@RequestParam @Min(1) Long studentId, @RequestParam @Min(0) double amountPaid) {
        logger.info("Student ID: {} is paying a fine of: {}", studentId, amountPaid);
        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            logger.warn("Student ID: {} not found.", studentId);
            throw new ResourceNotFoundException("Student not found");
        }

        double currentFine = student.getFineAmount();
        if (amountPaid >= currentFine) {
            student.setFineAmount(0);
            studentService.saveStudent(student);
            logger.info("Fine paid in full for Student ID: {}", studentId);
            return ResponseEntity.ok(new ApiResponse("Fine paid in full.", true));
        } else {
            student.setFineAmount(currentFine - amountPaid);
            studentService.saveStudent(student);
            logger.info("Partial fine payment accepted for Student ID: {}. Remaining fine: {}", studentId, student.getFineAmount());
            return ResponseEntity.ok(new ApiResponse("Partial fine payment accepted. Remaining fine: " + student.getFineAmount(), true));
        }
    }
}
