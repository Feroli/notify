package org.notify.service;


import org.notify.model.Category;
import org.notify.model.User;
import org.notify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> retrieveSubscribersOfCategory(Category category) {
        return repository.findBySubscribedCategories(category);
    }
}
