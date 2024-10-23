package com.example.Library.dto;

public class LoginRequest {
    private String name;
    private String password;
    private String role;  // "admin", "librarian", "student"

    // Constructors
    public LoginRequest() {
    }

    public LoginRequest(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String email) {
        this.name = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
