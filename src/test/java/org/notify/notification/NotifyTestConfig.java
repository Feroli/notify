package org.notify.notification;

import org.notify.model.Category;
import org.notify.model.ChannelType;
import org.notify.model.User;
import org.notify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class NotifyTestConfig {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initializeDatabase() {
        userRepository.saveAll(
                List.of(new User(1L, "Fer", "Fer@gmail.com", "787887", List.of(Category.FINANCE), List.of(ChannelType.SMS)),
                        new User(2L, "Ania", "Ania@gmail.com", "8793798543", List.of(Category.MOVIES), List.of(ChannelType.SMS, ChannelType.EMAIL)),
                        new User(2L, "James", "James@gmail.com", "345345", List.of(Category.MOVIES), List.of(ChannelType.SMS, ChannelType.EMAIL))));
    }
}
