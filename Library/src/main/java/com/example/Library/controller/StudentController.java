package com.example.Library.controller;

import com.example.Library.model.Book;
import com.example.Library.model.Student;
import com.example.Library.service.BookService;
import com.example.Library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private BookService bookService; // Service for managing books
    @Autowired
    private StudentService studentService; // Service for managing students

    @GetMapping("/searchBook")
    public ResponseEntity<List<Book>> searchBook(@RequestParam String authorName, @RequestParam String bookName) {
        // Logic to search for books by author name and book name
        List<Book> books = bookService.findByAuthorAndTitle(authorName, bookName);

        return ResponseEntity.ok(books);
    }

    @PostMapping("/takeBook")
    public ResponseEntity<String> takeBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        // Logic to issue the book to the student

        boolean isIssued = bookService.issueBook(studentId, bookId);
        if (isIssued) {
            return ResponseEntity.ok("Book issued successfully.");
        } else {
            return ResponseEntity.badRequest().body("Book could not be issued.");
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        // Logic to return the book from the student
        boolean isReturned = bookService.returnBook(studentId, bookId);
        if (isReturned) {
            return ResponseEntity.ok("Book returned successfully.");
        } else {
            return ResponseEntity.badRequest().body("Book could not be returned.");
        }
    }

    @PostMapping("/payFine")
    public ResponseEntity<String> payFine(@RequestParam Long studentId, @RequestParam double amountPaid) {
        // Logic to pay a fine
        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            return ResponseEntity.badRequest().body("Student not found");
        }

        double currentFine = student.getFineAmount();
        if (amountPaid >= currentFine) {
            student.setFineAmount(0);
            studentService.saveStudent(student);
            return ResponseEntity.ok("Fine paid in full.");
        } else {
            student.setFineAmount(currentFine - amountPaid);
            studentService.saveStudent(student);
            return ResponseEntity.ok("Partial fine payment accepted. Remaining fine: " + student.getFineAmount());
        }
    }

}
