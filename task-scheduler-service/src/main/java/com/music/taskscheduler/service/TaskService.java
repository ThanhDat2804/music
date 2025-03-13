package com.music.taskscheduler.service;

import com.music.taskscheduler.model.Task;

public interface TaskService {
    Task save(Task task);

    Task update(String id, Task task);

    Task deleteById(String id);

    Task getById(String id);
}
