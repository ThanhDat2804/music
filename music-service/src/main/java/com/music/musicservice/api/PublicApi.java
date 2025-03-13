package com.music.musicservice.api;

import com.music.musicservice.dto.BunnyVideoCallbackReq;
import com.music.musicservice.service.SongService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicApi {

    private final SongService songService;

    @PostMapping("/song/update-status")
    BunnyVideoCallbackReq updateStatus(@RequestBody BunnyVideoCallbackReq req) {
        return songService.updateSongStatus(req);
    }
}
