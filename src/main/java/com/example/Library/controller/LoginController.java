

package com.example.Library.controller;

import com.example.Library.dto.LoginRequest;
import com.example.Library.exception.ResourceNotFoundException;
import com.example.Library.model.Admin;
import com.example.Library.model.Librarian;
import com.example.Library.model.Student;
import com.example.Library.response.ApiResponse;
import com.example.Library.repository.AdminRepo;
import com.example.Library.repository.LibrarianRepo;
import com.example.Library.repository.StudentRepo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LibrarianRepo librarianRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // For password hashing and matching



    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        String name = loginRequest.getName();
        String password = loginRequest.getPassword();
        String role = loginRequest.getRole(); // Assuming role is provided in the login request (e.g., "admin", "librarian", "student")

        // Handle login based on the user's role
        if (role.equalsIgnoreCase("admin")) {
            Admin admin = adminRepo.findByName(name);
            if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
                logger.info("Admin login successful for user: {}", name);
                return ResponseEntity.ok(new ApiResponse("Admin login successful", true));
            }
        } else if (role.equalsIgnoreCase("librarian")) {
            Librarian librarian = librarianRepo.findByName(name);
            if (librarian != null && passwordEncoder.matches(password, librarian.getPassword())) {
                logger.info("Librarian login successful for user: {}", name);
                return ResponseEntity.ok(new ApiResponse("Librarian login successful", true));
            }
        } else if (role.equalsIgnoreCase("student")) {
            Student student = studentRepo.findByName(name);
            if (student != null && passwordEncoder.matches(password, student.getPassword())) {
                logger.info("Student login successful for user: {}", name);
                return ResponseEntity.ok(new ApiResponse("Student login successful", true));
            }
        }

        // If credentials are invalid
        logger.warn("Invalid credentials or role for user: {}", name);
        return ResponseEntity.status(401).body(new ApiResponse("Invalid credentials or role", false));
    }
}
