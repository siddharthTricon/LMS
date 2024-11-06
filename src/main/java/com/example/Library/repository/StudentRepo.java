package com.example.Library.repository;

import com.example.Library.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findAllByHasIssuedBooksTrue();

    Optional<Student> findById(Long studentId);

    Student findByName(String name);
}
