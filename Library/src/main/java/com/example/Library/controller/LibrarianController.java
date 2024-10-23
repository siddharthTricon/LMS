package com.example.Library.controller;


import com.example.Library.model.Student;
import com.example.Library.service.AdminService;
import com.example.Library.service.BookService;
import com.example.Library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/librarian")
public class LibrarianController {

    @Autowired
    private BookService bookService; // Service for managing books
    @Autowired
    private StudentService studentService; // Service for managing students
    @Autowired
    private AdminService adminService; // Service for sending information to the admin

    @PostMapping("/issueBook")
    public ResponseEntity<String> issueBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        System.out.println(studentId);
        // Logic to issue book to student
        boolean isIssued = bookService.issueBook(studentId, bookId);
        if (isIssued) {
            return ResponseEntity.ok("Book issued successfully.");
        } else {
            return ResponseEntity.badRequest().body("Book could not be issued.");
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        // Logic to return book from student
        boolean isReturned = bookService.returnBook(studentId, bookId);
        if (isReturned) {
            return ResponseEntity.ok("Book returned successfully.");
        } else {
            return ResponseEntity.badRequest().body("Book could not be returned.");
        }
    }

    @PostMapping("/addFine")
    public ResponseEntity<String> addFine(@RequestParam Long studentId, @RequestParam double fineAmount) {
        // Logic to add fine to student
        studentService.addFine(studentId, fineAmount);
        // Inform admin about the fine
        adminService.notifyAdmin(studentId, fineAmount);
        return ResponseEntity.ok("Fine added successfully.");
    }

    @GetMapping("/studentsWithBooks")
    public ResponseEntity<List<Student>> getStudentsWithBooks() {
        // Logic to get students who have issued books
        List<Student> students = studentService.getStudentsWithBooks();
        return ResponseEntity.ok(students);
    }

}
