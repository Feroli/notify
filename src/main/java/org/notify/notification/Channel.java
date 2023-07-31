package org.notify.notification;

import org.notify.model.User;

public interface Channel {

    public void sendNotification(User user, String message);
}
