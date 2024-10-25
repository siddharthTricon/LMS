//package com.example.Library.controller;
//
//import com.example.Library.model.Book;
//import com.example.Library.model.Student;
//import com.example.Library.model.Librarian;
//import com.example.Library.repository.BookRepo;
//import com.example.Library.repository.StudentRepo;
//import com.example.Library.repository.LibrarianRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private BookRepo bookRepository;
//
//    @Autowired
//    private StudentRepo studentRepository; // Repository for Student management
//
//    @Autowired
//    private LibrarianRepo librarianRepository; // Repository for Librarian management
//
////    @PostMapping("/addBook")
////    public ResponseEntity<String> addBook(@RequestBody Book book) {
////        bookRepository.save(book);
////        return ResponseEntity.ok("Book added successfully");
////    }
//
//
//    @PostMapping("/addBook")
//    public ResponseEntity<String> addBook(@RequestBody Book book) {
//        // Check if the book already exists by title and author
//        Book existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
//
//        if (existingBook != null) {
//            // If the book exists, increase availability
//            existingBook.setIsAvailable(existingBook.getIsAvailable() + 1);
//            bookRepository.save(existingBook);
//            return ResponseEntity.ok("Book availability updated.");
//        } else {
//            // If the book doesn't exist, save a new book record
//            book.setIsAvailable(1); // Set initial availability
//            bookRepository.save(book);
//            return ResponseEntity.ok("Book added successfully.");
//        }
//    }
//
//
////    @DeleteMapping("/deleteBook/{id}")
////    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
////        bookRepository.deleteById(id);
////        return ResponseEntity.ok("Book deleted successfully");
////    }
//
//    @DeleteMapping("/deleteBook/{id}")
//    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
//        Optional<Book> optionalBook = bookRepository.findById(id);
//
//        if (optionalBook.isPresent()) {
//            Book book = optionalBook.get();
//
//            // If availability is greater than 1, decrease the availability count
//            if (book.getIsAvailable() > 1) {
//                book.setIsAvailable(book.getIsAvailable() - 1);
//                bookRepository.save(book);
//                return ResponseEntity.ok("Book availability decreased by one.");
//            }
//            // If availability is 1, delete the book from the database
//            else {
//                bookRepository.deleteById(id);
//                return ResponseEntity.ok("Book deleted successfully.");
//            }
//        }
//        // If the book doesn't exist
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
//        }
//    }
//
//
//
//    @PostMapping("/addStudent")
//    public ResponseEntity<String> addStudent(@RequestBody Student student) {
//        studentRepository.save(student);
//        return ResponseEntity.ok("Student added successfully");
//    }
//
//    @DeleteMapping("/deleteStudent/{id}")
//    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
//        studentRepository.deleteById(id);
//        return ResponseEntity.ok("Student deleted successfully");
//    }
//
//    @PostMapping("/addLibrarian")
//    public ResponseEntity<String> addLibrarian(@RequestBody Librarian librarian) {
//        librarianRepository.save(librarian);
//        return ResponseEntity.ok("Librarian added successfully");
//    }
//
//    @DeleteMapping("/deleteLibrarian/{id}")
//    public ResponseEntity<String> deleteLibrarian(@PathVariable Long id) {
//        librarianRepository.deleteById(id);
//        return ResponseEntity.ok("Librarian deleted successfully");
//    }
//
//    @PostMapping("/addFine")
//    public ResponseEntity<String> addFine(@RequestParam Long studentId, @RequestParam double fineAmount) {
//        // Logic to add fine to student (implement accordingly)
//        Student student = studentRepository.findById(studentId).orElse(null);
//        if (student != null) {
//            student.setFineAmount(student.getFineAmount() + fineAmount);
//            studentRepository.save(student);
//            return ResponseEntity.ok("Fine added successfully");
//        }
//        return ResponseEntity.badRequest().body("Student not found");
//    }
//}



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
