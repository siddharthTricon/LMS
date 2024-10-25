package com.example.Library.service;

import com.example.Library.model.Book;

import java.util.List;

public interface BookService {

    /**
     * Issues a book to a student if the book is available and the student has no issued books.
     *
     * @param studentId the ID of the student who wants to issue the book
     * @param bookId the ID of the book to be issued
     * @return true if the book is successfully issued, false otherwise
     */
    boolean issueBook(Long studentId, Long bookId);

    /**
     * Returns a book from a student, marking it as available and updating any applicable fine.
     *
     * @param studentId the ID of the student returning the book
     * @param bookId the ID of the book being returned
     * @return true if the book is successfully returned, false otherwise
     */
    boolean returnBook(Long studentId, Long bookId);

    /**
     * Searches for books by partial matches on author name and title.
     *
     * @param authorName partial or full author name to search
     * @param bookName partial or full book title to search
     * @return list of books that match the search criteria
     */
    List<Book> findByAuthorAndTitle(String authorName, String bookName);
}
