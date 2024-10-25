package com.example.Library.service.impl;

import com.example.Library.model.Student;
import com.example.Library.model.Transaction;
import com.example.Library.repository.StudentRepo;
import com.example.Library.repository.TransactionRepo;
import com.example.Library.service.StudentService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private TransactionRepo transactionRepository;

    /**
     * Adds a fine to the student if there are any transactions indicating a late return.
     *
     * @param studentId   the ID of the student to fine
     * @param fineAmount  the fine amount to add
     */
    @Override
    public void addFine(Long studentId, double fineAmount) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Student s = student.get();
            // Check if there are any transactions with fines due to late return
            List<Transaction> transactions = transactionRepository.findByStudent(s);
            boolean hasLateReturns = transactions.stream()
                    .anyMatch(t -> t.getFine() > 0 && t.getReturnDate() != null);
            if (hasLateReturns) {
                s.setFineAmount(s.getFineAmount() + fineAmount);
                studentRepository.save(s);
                System.out.println("Fine of " + fineAmount + " added for student: " + s.getName());
            } else {
                System.out.println("No late returns found for student: " + s.getName());
            }
        } else {
            System.out.println("Student with ID: " + studentId + " not found.");
        }
    }

    /**
     * Handles the payment of a fine, either in full or partially.
     *
     * @param studentId   the ID of the student making the payment
     * @param amountPaid  the amount paid towards the fine
     * @return true if payment was successful, false otherwise
     */
    @Override
    public boolean payFine(Long studentId, double amountPaid) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Student s = student.get();
            double currentFine = s.getFineAmount();

            if (currentFine > 0) {
                if (amountPaid >= currentFine) {
                    s.setFineAmount(0); // Fine fully paid
                    studentRepository.save(s);
                    System.out.println("Fine fully paid for student: " + s.getName());
                } else {
                    s.setFineAmount(currentFine - amountPaid); // Fine partially paid
                    studentRepository.save(s);
                    System.out.println("Partial fine payment processed for student: " + s.getName());
                }
                return true;
            } else {
                System.out.println("No outstanding fine for student: " + s.getName());
                return false;
            }
        } else {
            System.out.println("Student with ID: " + studentId + " not found.");
            return false;
        }
    }

    /**
     * Retrieves a student by ID.
     *
     * @param studentId the ID of the student to find
     * @return the student found, or null if not found
     */
    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    /**
     * Saves a student entity.
     *
     * @param student the student to save
     */
    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    /**
     * Retrieves all students who currently have issued books.
     *
     * @return a list of students with active book issues
     */
    @Override
    public List<Student> getStudentsWithBooks() {
        return studentRepository.findAllByHasIssuedBooksTrue();
    }

    /**
     * Finds a student by ID with a minimum ID value validation.
     *
     * @param studentId the ID of the student to find
     * @return an Optional containing the student if found
     */
    @Override
    public Optional<Student> findById(@Min(1) Long studentId) {
        return studentRepository.findById(studentId);
    }
}
