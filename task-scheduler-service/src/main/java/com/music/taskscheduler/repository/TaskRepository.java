package com.music.taskscheduler.repository;

import com.music.taskscheduler.model.Status;
import com.music.taskscheduler.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TaskRepository extends JpaRepository<Task, String> {

    Page<Task> findByStatusAndProcessDateIsBefore(Status status, LocalDateTime currentTime, Pageable pageable);
}

