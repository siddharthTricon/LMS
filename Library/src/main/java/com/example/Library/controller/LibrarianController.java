//package com.example.Library.controller;
//
//
//import com.example.Library.model.Student;
//import com.example.Library.service.AdminService;
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
//@RequestMapping("/librarian")
//public class LibrarianController {
//
//    @Autowired
//    private BookService bookService; // Service for managing books
//    @Autowired
//    private StudentService studentService; // Service for managing students
//    @Autowired
//    private AdminService adminService; // Service for sending information to the admin
//
//    @PostMapping("/issueBook")
//    public ResponseEntity<String> issueBook(@RequestParam Long studentId, @RequestParam Long bookId) {
//        System.out.println(studentId);
//        // Logic to issue book to student
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
//        // Logic to return book from student
//        boolean isReturned = bookService.returnBook(studentId, bookId);
//        if (isReturned) {
//            return ResponseEntity.ok("Book returned successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("Book could not be returned.");
//        }
//    }
//
//    @PostMapping("/addFine")
//    public ResponseEntity<String> addFine(@RequestParam Long studentId, @RequestParam double fineAmount) {
//        // Logic to add fine to student
//        studentService.addFine(studentId, fineAmount);
//        // Inform admin about the fine
//        adminService.notifyAdmin(studentId, fineAmount);
//        return ResponseEntity.ok("Fine added successfully.");
//    }
//
//    @GetMapping("/studentsWithBooks")
//    public ResponseEntity<List<Student>> getStudentsWithBooks() {
//        // Logic to get students who have issued books
//        List<Student> students = studentService.getStudentsWithBooks();
//        return ResponseEntity.ok(students);
//    }
//
//}


package com.example.Library.controller;

import com.example.Library.model.Student;
import com.example.Library.response.ApiResponse;
import com.example.Library.service.AdminService;
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
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/librarian")
@Validated
public class LibrarianController {

    private static final Logger logger = LoggerFactory.getLogger(LibrarianController.class);

    @Autowired
    private BookService bookService; // Service for managing books

    @Autowired
    private StudentService studentService; // Service for managing students

    @Autowired
    private AdminService adminService; // Service for sending information to the admin

    @PostMapping("/issueBook")
    public ResponseEntity<ApiResponse> issueBook(@RequestParam @Min(1) Long studentId, @RequestParam @Min(1) Long bookId) {
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

    @PostMapping("/addFine")
    public ResponseEntity<ApiResponse> addFine(@RequestParam @Min(1) Long studentId, @RequestParam @Min(0) double fineAmount) {
        logger.info("Adding fine of {} to student ID: {}", fineAmount, studentId);
        Optional<Student> student = studentService.findById(studentId);
        if (student.isPresent()) {
            studentService.addFine(studentId, fineAmount);
            adminService.notifyAdmin(studentId, fineAmount);
            logger.info("Fine added successfully.");
            return ResponseEntity.ok(new ApiResponse("Fine added successfully.", true));
        } else {
            logger.warn("Student ID: {} not found.", studentId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Student not found.", false));
        }
    }

    @GetMapping("/studentsWithBooks")
    public ResponseEntity<List<Student>> getStudentsWithBooks() {
        logger.info("Fetching students with issued books.");
        List<Student> students = studentService.getStudentsWithBooks();
        logger.info("Found {} students with issued books.", students.size());
        return ResponseEntity.ok(students);
    }
}

