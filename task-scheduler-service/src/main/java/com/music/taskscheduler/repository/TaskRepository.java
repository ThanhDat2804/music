package com.music.taskscheduler.repository;

import com.music.taskscheduler.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
