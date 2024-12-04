package com.example.Library.service.impl;

import com.example.Library.model.Book;
import com.example.Library.model.Student;
import com.example.Library.model.Transaction;
import com.example.Library.repository.BookRepo;
import com.example.Library.repository.StudentRepo;
import com.example.Library.repository.TransactionRepo;
import com.example.Library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepo bookRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private TransactionRepo transactionRepository;

    /**
     * Issues a book to a student if the book is available and the student has no issued books.
     * Updates the student and book's state, and creates a transaction record.
     *
     * @param studentId the ID of the student who wants to issue the book
     * @param bookId the ID of the book to be issued
     * @return true if the book is successfully issued, false otherwise
     */
    @Override
    public boolean issueBook(Long studentId, Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (book.isPresent() && student.isPresent()) {
            Book b = book.get();
            Student s = student.get();

            if (b.getIsAvailable() == 1 && !s.isHasIssuedBooks()) {
                b.setIsAvailable(0);
                b.setIssuedToStudent(s);
                bookRepository.save(b);

                s.setHasIssuedBooks(true);
                studentRepository.save(s);

                Transaction transaction = new Transaction();
                transaction.setBook(b);
                transaction.setStudent(s);
                transaction.setIssueDate(new Date());
                transactionRepository.save(transaction);

                logger.info("Book [{}] issued to student [{}]", b.getTitle(), s.getName());
                return true;
            } else {
                logger.warn("Cannot issue book: Book is not available or student [{}] already has an issued book", s.getName());
                return false;
            }
        } else {
            logger.error("Book or student not found. Book ID: [{}], Student ID: [{}]", bookId, studentId);
            return false;
        }
    }

    /**
     * Returns a book from a student, marking it as available and updating any applicable fine.
     * Updates the student and book's state, and modifies the transaction record with a return date and fine if late.
     *
     * @param studentId the ID of the student returning the book
     * @param bookId the ID of the book being returned
     * @return true if the book is successfully returned, false otherwise
     */
    @Override
    public boolean returnBook(Long studentId, Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (book.isPresent() && student.isPresent()) {
            Book b = book.get();
            Student s = student.get();

            b.setIsAvailable(1);
            b.setIssuedToStudent(null);
            bookRepository.save(b);

            s.setHasIssuedBooks(false);
            studentRepository.save(s);

            Transaction transaction = transactionRepository.findByBookAndStudent(b, s);
            if (transaction != null) {
                Date today = new Date();
                transaction.setReturnDate(today);

                long daysLate = (today.getTime() - transaction.getIssueDate().getTime()) / (1000 * 60 * 60 * 24);
                if (daysLate > 14) {
                    double fine = (daysLate - 14) * 5;
                    transaction.setFine(fine);
                    s.setFineAmount(s.getFineAmount() + fine);
                    studentRepository.save(s);

                    logger.info("Fine of [{}] imposed on student [{}] for late return of book [{}]", fine, s.getName(), b.getTitle());
                }
                transactionRepository.save(transaction);
            } else {
                logger.warn("No transaction found for Book ID: [{}] and Student ID: [{}]", bookId, studentId);
            }

            logger.info("Book [{}] returned by student [{}]", b.getTitle(), s.getName());
            return true;
        } else {
            logger.error("Book or student not found for return. Book ID: [{}], Student ID: [{}]", bookId, studentId);
            return false;
        }
    }

    /**
     * Searches for books by partial matches on author name and title.
     *
     * @param authorName partial or full author name to search
     * @param bookName partial or full book title to search
     * @return list of books that match the search criteria
     */
    @Override
    public List<Book> findByAuthorAndTitle(String authorName, String bookName) {
        logger.info("Searching books by author [{}] and title [{}]", authorName, bookName);
        return bookRepository.findByAuthorContainingAndTitleContaining(authorName, bookName);
    }
}
