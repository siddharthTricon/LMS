//package com.example.Library.controller;
//
//import com.example.Library.dto.LoginRequest;
//import com.example.Library.model.Admin;
//import com.example.Library.model.Librarian;
//import com.example.Library.model.Student;
//import com.example.Library.repository.AdminRepo;
//import com.example.Library.repository.LibrarianRepo;
//import com.example.Library.repository.StudentRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//public class LoginController {
//
//    @Autowired
//    private LibrarianRepo librarianRepo;
//
//    @Autowired
//    private AdminRepo adminRepo;
//
//    @Autowired
//    private StudentRepo studentRepo;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        String name = loginRequest.getName();
//        String password = loginRequest.getPassword();
//        String role = loginRequest.getRole();  // Assuming role is provided in login request (e.g., "admin", "librarian", "student")
//
//        // Handle login based on the user's role
//        if (role.equalsIgnoreCase("admin")) {
//            Admin admin = adminRepo.findByName(name);
//            if (admin != null && admin.getPassword().equals(password)) {
//                return ResponseEntity.ok("Admin login successful");
//            }
//        } else if (role.equalsIgnoreCase("librarian")) {
//            Librarian librarian = librarianRepo.findByName(name);
//            if (librarian != null && librarian.getPassword().equals(password)) {
//                return ResponseEntity.ok("Librarian login successful");
//            }
//        } else if (role.equalsIgnoreCase("student")) {
//            Student student = studentRepo.findByName(name);
//            if (student != null && student.getPassword().equals(password)) {
//                return ResponseEntity.ok("Student login successful");
//            }
//        }
//
//        // If credentials are invalid
//        return ResponseEntity.status(401).body("Invalid credentials or role");
//    }
//}





package com.example.Library.controller;

import com.example.Library.dto.LoginRequest;
import com.example.Library.model.Admin;
import com.example.Library.model.Librarian;
import com.example.Library.model.Student;
import com.example.Library.repository.AdminRepo;
import com.example.Library.repository.LibrarianRepo;
import com.example.Library.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private LibrarianRepo librarianRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // For password hashing and matching

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String name = loginRequest.getName();
        String password = loginRequest.getPassword();
        String role = loginRequest.getRole();  // Assuming role is provided in login request (e.g., "admin", "librarian", "student")

        // Handle login based on the user's role
        if (role.equalsIgnoreCase("admin")) {
            Admin admin = adminRepo.findByName(name);
            if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
                return ResponseEntity.ok("Admin login successful");
            }
        } else if (role.equalsIgnoreCase("librarian")) {
            Librarian librarian = librarianRepo.findByName(name);
            if (librarian != null && passwordEncoder.matches(password, librarian.getPassword())) {
                return ResponseEntity.ok("Librarian login successful");
            }
        } else if (role.equalsIgnoreCase("student")) {
            Student student = studentRepo.findByName(name);
            if (student != null && passwordEncoder.matches(password, student.getPassword())) {
                return ResponseEntity.ok("Student login successful");
            }
        }
        // If credentials are invalid
        return ResponseEntity.status(401).body("Invalid credentials or role");
    }

    // Delete admin by ID
//    @DeleteMapping("/admin/{id}")
//    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
//        if (adminRepo.existsById(id)) {
//            adminRepo.deleteById(id);
//            return ResponseEntity.ok("Admin deleted successfully");
//        } else {
//            return ResponseEntity.status(404).body("Admin not found");
//        }
//    }
//
//    // Delete librarian by ID
//    @DeleteMapping("/librarian/{id}")
//    public ResponseEntity<String> deleteLibrarian(@PathVariable Long id) {
//        if (librarianRepo.existsById(id)) {
//            librarianRepo.deleteById(id);
//            return ResponseEntity.ok("Librarian deleted successfully");
//        } else {
//            return ResponseEntity.status(404).body("Librarian not found");
//        }
//    }
//
//    // Delete student by ID
//    @DeleteMapping("/student/{id}")
//    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
//        if (studentRepo.existsById(id)) {
//            studentRepo.deleteById(id);
//            return ResponseEntity.ok("Student deleted successfully");
//        } else {
//            return ResponseEntity.status(404).body("Student not found");
//        }
//    }
}
