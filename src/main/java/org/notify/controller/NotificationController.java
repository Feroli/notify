package org.notify.controller;


import org.notify.model.NotificationLogEntry;
import org.notify.notification.NotificationSystem;
import org.notify.service.NotificationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationSystem notificationSystem;

    @Autowired
    private NotificationLogService logService;

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

    @GetMapping("/logs")
    public ResponseEntity<List<NotificationLogEntry>> getNotificationLogs() {
        try {
            return ResponseEntity.ok(logService.retrieveLogEntriesByTimestampDesc());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
}




