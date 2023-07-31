package org.notify.repository;

import org.notify.model.Category;
import org.notify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    List<User> findBySubscribedCategories(Category category);
}
