package com.example.Library.service.impl;

import com.example.Library.model.Admin;
import com.example.Library.repository.AdminRepo;
import com.example.Library.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminRepo adminRepository;

    /**
     * Notify admins about a fine on a student.
     *
     * @param studentId  The ID of the student being fined.
     * @param fineAmount The fine amount.
     */
    @Override
    public void notifyAdmin(Long studentId, double fineAmount) {
        List<Admin> admins = getAdmins();

        if (admins.isEmpty()) {
            logger.warn("No admins found to notify about the fine for student ID: {}", studentId);
            return;
        }

        // Notify each admin
        for (Admin admin : admins) {
            sendNotification(admin, studentId, fineAmount);
        }
    }

    /**
     * Fetch all admins.
     *
     * @return List of all admin entities.
     */
    private List<Admin> getAdmins() {
        try {
            return adminRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching admins from the repository", e);
            throw new RuntimeException("Failed to fetch admins");
        }
    }

    /**
     * Send notification to a specific admin.
     *
     * @param admin      The admin to notify.
     * @param studentId  The student being fined.
     * @param fineAmount The amount of the fine.
     */
    private void sendNotification(Admin admin, Long studentId, double fineAmount) {
        // This is where you would integrate email, SMS, or another notification service.
        // For now, we're logging it.
        logger.info("Notifying Admin: {} about fine on Student ID: {} with fine amount: {}",
                admin.getName(), studentId, fineAmount);

        // You can later replace this with an actual notification service, e.g.:
        // notificationService.sendEmail(admin.getEmail(), "Fine Notification", "Student " + studentId + " has a fine of $" + fineAmount);
    }
}
