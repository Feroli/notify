package org.notify.repository;

import org.notify.model.Category;
import org.notify.model.NotificationLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogEntryRepository extends JpaRepository<NotificationLogEntry, Long> {
    List<NotificationLogEntry> findByCategory(Category category);
    List<NotificationLogEntry> findAllByOrderByTimestampDesc();

}
