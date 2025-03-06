package com.music.musicservice.model.projection;

import com.music.musicservice.dto.SongProjectionDto;
import com.music.musicservice.model.Status;

import java.util.List;

public class AlbumWithSongProjection {
    private String id;
    private String name;
    private Status status;
    private List<SongProjectionDto> songs;
}
