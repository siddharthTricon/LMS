package com.example.Library.service;

import com.example.Library.model.Admin;
import com.example.Library.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepository;

    public void notifyAdmin(Long studentId, double fineAmount) {
        // Fetch the admin details (can be multiple admins)
        List<Admin> admins = adminRepository.findAll();

        // Logic to inform admins (e.g., logging it, sending notification)
        for (Admin admin : admins) {
            System.out.println("Notifying Admin: " + admin.getName() +
                    " about fine on student ID: " + studentId +
                    " with fine amount: " + fineAmount);
        }
    }

}
