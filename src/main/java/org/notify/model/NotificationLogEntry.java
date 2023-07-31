package org.notify.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class NotificationLogEntry {

    @Id
    @GeneratedValue
    private Long id;
    private Category category;
    @ManyToOne
    private User user;
    private String message;
    @CreationTimestamp
    private LocalDateTime timestamp;

    public NotificationLogEntry() {}

    public NotificationLogEntry(Category category, User user, String message) {
        this.category = category;
        this.user = user;
        this.message = message;
    }
}
