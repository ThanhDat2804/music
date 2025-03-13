package com.music.taskscheduler.service.impl;

import com.music.taskscheduler.model.Task;
import com.music.taskscheduler.repository.TaskRepository;
import com.music.taskscheduler.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(String id, Task task) {
        Task task1 = getById(id);
        if(isNull(task1)) {
            throw new RuntimeException("Task not found" + id);
        }
        task.setId(id);
        return taskRepository.save(task);
    }

    @Override
    public Task deleteById(String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getById(String id) {
        return taskRepository.findById(id).orElse(null);
    }
}
