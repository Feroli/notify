package org.notify.controller;

import lombok.Getter;
import lombok.Setter;
import org.notify.model.Category;

@Getter
@Setter
public class NotificationRequest {

    private Category category;
    private String message;

    public NotificationRequest(Category category, String message) {
        this.category = category;
        this.message = message;
    }
}
