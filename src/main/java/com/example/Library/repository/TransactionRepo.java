package com.example.Library.repository;

import com.example.Library.model.Book;
import com.example.Library.model.Student;
import com.example.Library.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Transaction findByBookAndStudent(Book book, Student student);

    List<Transaction> findByStudent(Student s);
}
