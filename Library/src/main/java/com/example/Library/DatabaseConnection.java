//package com.example.Library;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseConnection {
//    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/library_system";
//        String user = "Siddharth";
//        String password = "Siddharth@03";
//
//        try {
//            // Establish the connection
//            Connection connection = DriverManager.getConnection(url, user, password);
//            System.out.println("Database connected successfully!");
//
//            // Don't forget to close the connection
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Connection failed: " + e.getMessage());
//        }
//    }
//}
