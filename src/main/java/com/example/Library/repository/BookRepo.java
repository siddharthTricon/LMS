package com.example.Library.repository;

import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByAuthorContainingAndTitleContaining(String authorName, String bookName);

    Book findByTitleAndAuthor(String title, String author);
}
