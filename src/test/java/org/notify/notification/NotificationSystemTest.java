package org.notify.notification;

import org.junit.jupiter.api.Test;
import org.notify.model.Category;
import org.notify.service.NotificationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(NotifyTestConfig.class)
public class NotificationSystemTest {

    @Autowired
    NotificationSystem notificationSystem;

    @Autowired
    NotificationLogService logService;

    @Test
    public void sendNotificationWithFinanceCategory() {
        notificationSystem.processNotification(Category.FINANCE, "a recession is coming");
        assertEquals(1, logService.retrieveLogEntriesWithCategory(Category.FINANCE).size());
    }

    @Test
    public void sendNotificationWithMoviesCategory() {
        notificationSystem.processNotification(Category.MOVIES, "Up and away");
        assertEquals(2, logService.retrieveLogEntriesWithCategory(Category.MOVIES).size());
    }
}