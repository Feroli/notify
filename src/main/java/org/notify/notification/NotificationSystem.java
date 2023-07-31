package org.notify.notification;

import org.notify.model.Category;
import org.notify.model.ChannelType;
import org.notify.model.NotificationLogEntry;
import org.notify.service.NotificationLogService;
import org.notify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationSystem {

    @Autowired
    private NotificationLogService logService;

    @Autowired
    private ChannelFactory channelFactory;

    @Autowired
    private UserService userService;

    public NotificationSystem(NotificationLogService logService, ChannelFactory channelFactory) {
        this.logService = logService;
        this.channelFactory = channelFactory;
    }

    public void processNotification(Category category, String message) {
        userService.retrieveSubscribersOfCategory(category).forEach(user ->  {
            for (ChannelType channel : user.getNotificationChannels()) {
                channelFactory.getChannelByType(channel).sendNotification(user, message);
                logService.addLogEntry(new NotificationLogEntry(category, user, message));
            }
        });
    }
}
