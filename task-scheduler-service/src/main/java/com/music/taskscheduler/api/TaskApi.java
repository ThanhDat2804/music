package com.music.taskscheduler.api;

import com.music.taskscheduler.model.Task;
import com.music.taskscheduler.service.TaskService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskApi {

    private final TaskService taskService;

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/{id}")
    public Task update(@RequestBody Task task,
                       @PathVariable String id) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable String id) {
         taskService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable String id) {
        return taskService.getById(id);
    }

}
