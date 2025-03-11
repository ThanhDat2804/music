package com.music.musicservice.proxy;

import com.music.musicservice.config.FeignClientConfig;
import com.music.musicservice.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "task-scheduler-service", configuration = FeignClientConfig.class)
public interface TaskProxy {

    @PostMapping("/task/users/{userId}")
    TaskDto createTask(@RequestBody TaskDto taskDto);
}
