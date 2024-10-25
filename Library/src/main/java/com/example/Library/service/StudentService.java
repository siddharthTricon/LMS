package com.example.Library.service;

import com.example.Library.model.Student;

import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    void addFine(Long studentId, double fineAmount);

    boolean payFine(Long studentId, double amountPaid);

    Student findStudentById(Long studentId);

    void saveStudent(Student student);

    List<Student> getStudentsWithBooks();

    Optional<Student> findById(@Min(1) Long studentId);
}
