package org.notify.controller;


import org.notify.notification.NotificationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationSystem notificationSystem;

    @PostMapping("/sendNotification")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        try {
            if (request.getMessage() == null || request.getMessage().isEmpty()) {
                return ResponseEntity.badRequest().body("Message cannot be empty");
            }

            if (request.getCategory() == null) {
                return ResponseEntity.badRequest().body("Category cannot be missing");
            }

            notificationSystem.processNotification(request.getCategory(), request.getMessage());

            return ResponseEntity.ok("Notification sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send notification: " + e.getMessage());
        }
    }
}




