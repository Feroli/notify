package org.notify.notification;

import org.notify.model.ChannelType;
import org.springframework.stereotype.Component;

@Component
public class ChannelFactory {

    public Channel getChannelByType(ChannelType channelType) {
        return switch (channelType) {
            case EMAIL -> new Email();
            case PUSH -> new Push();
            case SMS -> new SMS();
        };
    }
}
