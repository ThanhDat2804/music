package com.music.taskscheduler.service.impl;

import com.music.taskscheduler.model.Status;
import com.music.taskscheduler.model.Task;
import com.music.taskscheduler.proxy.MusicProxy;
import com.music.taskscheduler.service.TaskSchedulerService;
import com.music.taskscheduler.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskSchedulerServiceImpl implements TaskSchedulerService {

    private static final String RELEASE_SONG = "RELEASE_SONG";
    private static final String RELEASE_ALBUM = "RELEASE_ALBUM";
    private final TaskService taskService;
    private final MusicProxy musicProxy;

    @Override
    @Scheduled(fixedDelay= 2000L)
    public void processTask() {
        List<Task> tasks = taskService.getTasks(Status.PENDING,0,100);
    }

    private void process(List<Task> tasks) {
        if (CollectionUtils.isEmpty(tasks)) {
            return;
        }

        tasks.forEach(task -> {
            Map<String, Object> data = task.getData();

            if (data.containsKey(RELEASE_SONG)) {
                String songId = (String) data.get(RELEASE_SONG);
                musicProxy.updateSongStatus(songId, "PUBLISHED");
                successProcess(task);
            }
            else if (data.containsKey(RELEASE_ALBUM)) {
                String albumId = (String) data.get(RELEASE_ALBUM);
                musicProxy.updateAlbumStatus(albumId, "PUBLISHED");
                successProcess(task);
            }
            else {
                task.setStatus(Status.FAILED);
                task.setMessage("Task Process not supported");
                taskService.update(task.getId(), task);
            }
        });
    }

    private void successProcess(Task task){
        task.setStatus(Status.DONE);
        task.setMessage("Task Process");
        taskService.update(task.getId(), task);
    }
}
