package com.music.musicservice.service;

import com.music.musicservice.model.projection.SongProjection;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VoiceSearchService {
    List<SongProjection> searchSongsByVoice(MultipartFile file) throws IOException;
}
