package com.example.Library.service;

import com.example.Library.model.Student;
import com.example.Library.model.Transaction;
import com.example.Library.repository.StudentRepo;
import com.example.Library.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepository;
    @Autowired
    private TransactionRepo transactionRepository;

//    public void addFine(Long studentId, double fineAmount) {
//        // Fetch the student by ID
//        Optional<Student> student = studentRepository.findById(studentId);
//        if (student.isPresent()) {
//            Student s = student.get();
//            s.setFineAmount(s.getFineAmount() + fineAmount); // Add fine to the student
//            studentRepository.save(s); // Save updated student
//            System.out.println("Fine added to student: " + s.getName() + " with fine amount: " + fineAmount);
//        }
//    }


    public void addFine(Long studentId, double fineAmount) {
        // Fetch the student by ID
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Student s = student.get();
            // Ensure that the fine is related to a late return
            List<Transaction> transactions = transactionRepository.findByStudent(s);
            boolean hasLateReturns = transactions.stream().anyMatch(
                    t -> t.getFine() > 0 && t.getReturnDate() != null
            );
            if (hasLateReturns) {
                s.setFineAmount(s.getFineAmount() + fineAmount); // Add fine to the student
                studentRepository.save(s); // Save updated student
                System.out.println("Fine added to student: " + s.getName() + " with fine amount: " + fineAmount);
            }
        }
    }

    boolean payFine(Long studentId, double amountPaid) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Student s = student.get();
            double currentFine = s.getFineAmount();

            // Check if the fine is greater than zero
            if (currentFine > 0) {
                if (amountPaid >= currentFine) {
                    s.setFineAmount(0); // Fine fully paid
                    studentRepository.save(s);
                    System.out.println("Fine fully paid for student: " + s.getName());
                } else {
                    s.setFineAmount(currentFine - amountPaid); // Fine partially paid
                    studentRepository.save(s);
                    System.out.println("Partial fine payment accepted for student: " + s.getName());
                }
                return true;
            } else {
                System.out.println("No fine due for student: " + s.getName());
                return false;
            }
        } else {
            System.out.println("Student with ID: " + studentId + " not found.");
            return false;
        }
    }

    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getStudentsWithBooks() {
        // Logic to find students who have issued books (e.g., filter by whether they have active books)
        return studentRepository.findAllByHasIssuedBooksTrue(); // Modify as necessary
    }

}
