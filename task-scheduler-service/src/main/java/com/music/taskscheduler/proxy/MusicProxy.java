package com.music.taskscheduler.proxy;

import com.music.taskscheduler.config.FeignClientConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "music-service", configuration = FeignClientConfig.class)
public interface MusicProxy {

    @PutMapping("${music/song/{songId}/status}")
    void updateSongStatus(@PathVariable String songID, @RequestParam String status);

    @PutMapping("${music/album/{albumId}/status}")
    void updateAlbumStatus(@PathVariable String songID, @RequestParam String status);
}

