package com.music.musicservice.api;

import com.music.musicservice.model.projection.SongProjection;
import com.music.musicservice.service.VoiceSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/music")
public class VoiceSearchController {

    private final VoiceSearchService voiceSearchService;

    public VoiceSearchController(VoiceSearchService voiceSearchService) {
        this.voiceSearchService = voiceSearchService;
    }

    @PostMapping("/voice-search")
    public ResponseEntity<?> searchMusicByVoice(@RequestParam("file") MultipartFile file) {
        try {
            List<SongProjection> songs = voiceSearchService.searchSongsByVoice(file);
            return ResponseEntity.ok(Map.of("results", songs));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Lỗi xử lý file âm thanh");
        }
    }
}


