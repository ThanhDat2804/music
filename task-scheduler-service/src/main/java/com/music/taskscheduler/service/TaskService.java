package com.music.taskscheduler.service;

import com.music.taskscheduler.model.Status;
import com.music.taskscheduler.model.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);

    Task update(String id, Task task);

    void deleteById(String id);

    Task getById(String id);

    List<Task> getTasks(Status status, int page, int pageSize);
}
