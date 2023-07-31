package org.notify.service;

import org.notify.model.Category;
import org.notify.model.NotificationLogEntry;
import org.notify.repository.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationLogService {

    @Autowired
    LogEntryRepository logEntryRepository;

    public void addLogEntry(NotificationLogEntry logEntry) {
        logEntryRepository.save(logEntry);
    }

    public List<NotificationLogEntry> retrieveLogEntries() {
        return logEntryRepository.findAll();
    }
    public List<NotificationLogEntry> retrieveLogEntriesByTimestampDesc() {
        return logEntryRepository.findAllByOrderByTimestampDesc();
    }

    public List<NotificationLogEntry> retrieveLogEntriesWithCategory(Category category) {
        return logEntryRepository.findByCategory(category);
    }
}

