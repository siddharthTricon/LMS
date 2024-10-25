
package com.example.Library.controller;

import com.example.Library.exception.ResourceNotFoundException;
import com.example.Library.model.Book;
import com.example.Library.model.Student;
import com.example.Library.model.Librarian;
import com.example.Library.repository.BookRepo;
import com.example.Library.repository.StudentRepo;
import com.example.Library.repository.LibrarianRepo;
import com.example.Library.response.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
@Validated
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private BookRepo bookRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private LibrarianRepo librarianRepository;

    @PostMapping("/addBook")
    public ResponseEntity<ApiResponse> addBook(@Valid @RequestBody Book book) {
        logger.info("Adding book: {}", book.getTitle());

        Book existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());

        if (existingBook != null) {
            existingBook.setIsAvailable(existingBook.getIsAvailable() + 1);
            bookRepository.save(existingBook);
            return ResponseEntity.ok(new ApiResponse("Book availability updated.", true));
        } else {
            book.setIsAvailable(1);
            bookRepository.save(book);
            return ResponseEntity.ok(new ApiResponse("Book added successfully.", true));
        }
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long id) {
        logger.info("Deleting book with ID: {}", id);
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            if (book.getIsAvailable() > 1) {
                book.setIsAvailable(book.getIsAvailable() - 1);
                bookRepository.save(book);
                return ResponseEntity.ok(new ApiResponse("Book availability decreased by one.", true));
            } else {
                bookRepository.deleteById(id);
                return ResponseEntity.ok(new ApiResponse("Book deleted successfully.", true));
            }
        } else {
            throw new ResourceNotFoundException("Book not found with ID: " + id);
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<ApiResponse> addStudent(@Valid @RequestBody Student student) {
        logger.info("Adding student: {}", student.getName());
        studentRepository.save(student);
        return ResponseEntity.ok(new ApiResponse("Student added successfully.", true));
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id) {
        logger.info("Deleting student with ID: {}", id);
        studentRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Student deleted successfully.", true));
    }

    @PostMapping("/addLibrarian")
    public ResponseEntity<ApiResponse> addLibrarian(@Valid @RequestBody Librarian librarian) {
        logger.info("Adding librarian: {}", librarian.getName());
        librarianRepository.save(librarian);
        return ResponseEntity.ok(new ApiResponse("Librarian added successfully.", true));
    }

    @DeleteMapping("/deleteLibrarian/{id}")
    public ResponseEntity<ApiResponse> deleteLibrarian(@PathVariable Long id) {
        logger.info("Deleting librarian with ID: {}", id);
        librarianRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Librarian deleted successfully.", true));
    }

    @PostMapping("/addFine")
    public ResponseEntity<ApiResponse> addFine(@RequestParam Long studentId, @RequestParam double fineAmount) {
        logger.info("Adding fine of {} for student ID: {}", fineAmount, studentId);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
            student.setFineAmount(student.getFineAmount() + fineAmount);
            studentRepository.save(student);
            return ResponseEntity.ok(new ApiResponse("Fine added successfully.", true));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Student not found", false));
    }
}
