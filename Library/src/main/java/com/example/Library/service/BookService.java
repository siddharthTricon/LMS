//package com.example.Library.service;
//
//import com.example.Library.model.Book;
//import com.example.Library.model.Student;
//import com.example.Library.repository.BookRepo;
//import com.example.Library.repository.StudentRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BookService {
//
//    @Autowired
//    private BookRepo bookRepository;
//    @Autowired
//    private StudentRepo studentRepository;
//
////    public boolean issueBook(Long studentId, Long bookId) {
////        // Check if book exists and is available
////        System.out.println(bookId);
////        Optional<Book> book = bookRepository.findById(bookId);
////        System.out.println("BookName "+book.isPresent());
////        Optional<Student> student = studentRepository.findById(studentId);
////        System.out.println("Student "+student.isPresent());
////
////        if (book.isPresent() && student.isPresent()) {
////            Book b = book.get();
////            Student s = student.get();
////            System.out.println("BookDetails "+b.getTitle());
////
////            if (b.isAvailable()) {
////                b.setAvailable(false); // Mark book as issued
////                b.setIssuedToStudent(s); // Assign the book to the student
////                bookRepository.save(b); // Save book state
////
////                // Update student records
////                s.getIssuedBooks().add(b);
////                studentRepository.save(s);
////
////                System.out.println("Book issued to student: " + s.getName());
////                return true;
////            } else {
////                System.out.println("Book is not available");
////                return false;
////            }
////        }
////
////        return false;
////    }
//
//    public boolean issueBook(Long studentId, Long bookId) {
//        // Check if book exists and is available
//        Optional<Book> book = bookRepository.findById(bookId);
//        Optional<Student> student = studentRepository.findById(studentId);
//
//        if (book.isPresent() && student.isPresent()) {
//            Book b = book.get();
//            Student s = student.get();
//
//            if (b.isAvailable()) {
//                b.setAvailable(false); // Mark book as issued
//                b.setIssuedToStudent(s); // Assign the book to the student
//                bookRepository.save(b); // Save book state
//
//                // Update student records
//                s.getIssuedBooks().add(b);
//                s.setHasIssuedBooks(true);  // Set the hasIssuedBooks flag to true
//                studentRepository.save(s);
//
//                System.out.println("Book issued to student: " + s.getName());
//                return true;
//            } else {
//                System.out.println("Book is not available");
//                return false;
//            }
//        }
//
//        return false;
//    }
//
//    public boolean returnBook(Long studentId, Long bookId) {
//        Optional<Book> book = bookRepository.findById(bookId);
//        Optional<Student> student = studentRepository.findById(studentId);
//
//        if (book.isPresent() && student.isPresent()) {
//            Book b = book.get();
//            Student s = student.get();
//
//            // Mark book as available again
//            b.setAvailable(true);
//            b.setIssuedToStudent(null); // Clear the student field
//            bookRepository.save(b);
//
//            // Remove the book from the student's issued books
//            s.getIssuedBooks().remove(b);
//
//            // Check if the student still has any issued books
//            if (s.getIssuedBooks().isEmpty()) {
//                s.setHasIssuedBooks(false);  // Set the flag to false if no more books are issued
//            }
//
//            studentRepository.save(s);
//
//            System.out.println("Book returned by student: " + s.getName());
//            return true;
//        }
//
//        return false;
//    }
//
//    // Return book method
////    public boolean returnBook(Long studentId, Long bookId) {
////        // Find the book by its ID
////        Optional<Book> bookOptional = bookRepository.findById(bookId);
////
////        if (bookOptional.isPresent()) {
////            Book book = bookOptional.get();
////
////            // Check if the book was issued to the correct student
////            if (book.getIssuedToStudent() != null && book.getIssuedToStudent().equals(studentId)) {
////                // Only update the issued_to_student_id column to null
////                book.setIssuedToStudent(null);
////                bookRepository.save(book); // Save changes to the book
////
////                return true; // Book return successful
////            }
////        }
////
////        return false; // Book not found or was not issued to this student
////    }
//
//
//    public List<Book> findByAuthorAndTitle(String authorName, String bookName) {
//        // Logic to find books by author and title
//        return bookRepository.findByAuthorContainingAndTitleContaining(authorName, bookName);
//    }
//
//}



//package com.example.Library.service;
//
//import com.example.Library.model.Book;
//import com.example.Library.model.Student;
//import com.example.Library.model.Transaction;
//import com.example.Library.repository.BookRepo;
//import com.example.Library.repository.StudentRepo;
//import com.example.Library.repository.TransactionRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BookService {
//
//    @Autowired
//    private BookRepo bookRepository;
//
//    @Autowired
//    private StudentRepo studentRepository;
//
//    @Autowired
//    private TransactionRepo transactionRepository;
//
//    public boolean issueBook(Long studentId, Long bookId) {
//        // Check if book exists and is available
//        Optional<Book> book = bookRepository.findById(bookId);
//        Optional<Student> student = studentRepository.findById(studentId);
//
//        if (book.isPresent() && student.isPresent()) {
//            Book b = book.get();
//            Student s = student.get();
//
//            // Ensure the book is available and student has no issued books
//            if (b.isAvailable() && !s.isHasIssuedBooks()) {
//                b.setisAvailable(false);
//                b.setIssuedToStudent(s);
//                bookRepository.save(b);
//
//                s.setHasIssuedBooks(true);
//                studentRepository.save(s);
//
//                // Create transaction with issueDate
//                Transaction transaction = new Transaction();
//                transaction.setBook(b);
//                transaction.setStudent(s);
//                transaction.setIssueDate(new Date());
//                transactionRepository.save(transaction);
//
//                System.out.println("Book issued to student: " + s.getName());
//                return true;
//            } else {
//                System.out.println("Book not available or student already has a book.");
//                return false;
//            }
//        }
//        return false;
//    }
//
//    public boolean returnBook(Long studentId, Long bookId) {
//        Optional<Book> book = bookRepository.findById(bookId);
//        Optional<Student> student = studentRepository.findById(studentId);
//
//        if (book.isPresent() && student.isPresent()) {
//            Book b = book.get();
//            Student s = student.get();
//
//            // Mark book as available
//            b.setAvailable(true);
//            b.setIssuedToStudent(null);  // Clear issuedToStudent
//            bookRepository.save(b);
//
//            // Remove from student and update their state
//            s.setHasIssuedBooks(false);
//            studentRepository.save(s);
//
//            // Update transaction returnDate and calculate fine if late
//            Transaction transaction = transactionRepository.findByBookAndStudent(b, s);
//            if (transaction != null) {
//                Date today = new Date();
//                transaction.setReturnDate(today);
//
//                long daysLate = (today.getTime() - transaction.getIssueDate().getTime()) / (1000 * 60 * 60 * 24);
//                if (daysLate > 14) {  // Assuming a 2-week borrowing period
//                    double fine = (daysLate - 14) * 5;  // Fine of 5 units per day late
//                    transaction.setFine(fine);
//                    s.setFineAmount(s.getFineAmount() + fine);
//                    studentRepository.save(s);
//                }
//                transactionRepository.save(transaction);
//            }
//
//            System.out.println("Book returned by student: " + s.getName());
//            return true;
//        }
//        return false;
//    }
//
//    public List<Book> findByAuthorAndTitle(String authorName, String bookName) {
//        return bookRepository.findByAuthorContainingAndTitleContaining(authorName, bookName);
//    }
//}


package com.example.Library.service;

import com.example.Library.model.Book;
import com.example.Library.model.Student;
import com.example.Library.model.Transaction;
import com.example.Library.repository.BookRepo;
import com.example.Library.repository.StudentRepo;
import com.example.Library.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private TransactionRepo transactionRepository;

    public boolean issueBook(Long studentId, Long bookId) {
        // Check if book exists and is available
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (book.isPresent() && student.isPresent()) {
            Book b = book.get();
            Student s = student.get();

            // Ensure the book is available (1 means available) and the student has no issued books
            if (b.getIsAvailable() == 1 && !s.isHasIssuedBooks()) {
                b.setIsAvailable(0); // Mark book as unavailable (0)
                b.setIssuedToStudent(s);
                bookRepository.save(b);

                s.setHasIssuedBooks(true);
                studentRepository.save(s);

                // Create transaction with issueDate
                Transaction transaction = new Transaction();
                transaction.setBook(b);
                transaction.setStudent(s);
                transaction.setIssueDate(new Date());
                transactionRepository.save(transaction);

                System.out.println("Book issued to student: " + s.getName());
                return true;
            } else {
                System.out.println("Book not available or student already has a book.");
                return false;
            }
        }
        return false;
    }

    public boolean returnBook(Long studentId, Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (book.isPresent() && student.isPresent()) {
            Book b = book.get();
            Student s = student.get();

            // Mark book as available (1 means available)
            b.setIsAvailable(1);
            b.setIssuedToStudent(null);  // Clear issuedToStudent
            bookRepository.save(b);

            // Remove from student and update their state
            s.setHasIssuedBooks(false);
            studentRepository.save(s);

            // Update transaction returnDate and calculate fine if late
            Transaction transaction = transactionRepository.findByBookAndStudent(b, s);
            if (transaction != null) {
                Date today = new Date();
                transaction.setReturnDate(today);

                long daysLate = (today.getTime() - transaction.getIssueDate().getTime()) / (1000 * 60 * 60 * 24);
                if (daysLate > 14) {  // Assuming a 2-week borrowing period
                    double fine = (daysLate - 14) * 5;  // Fine of 5 units per day late
                    transaction.setFine(fine);
                    s.setFineAmount(s.getFineAmount() + fine);
                    studentRepository.save(s);
                }
                transactionRepository.save(transaction);
            }

            System.out.println("Book returned by student: " + s.getName());
            return true;
        }
        return false;
    }

    public List<Book> findByAuthorAndTitle(String authorName, String bookName) {
        return bookRepository.findByAuthorContainingAndTitleContaining(authorName, bookName);
    }
}













