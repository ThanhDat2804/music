package com.music.musicservice.model.projection;

import com.music.musicservice.dto.SongProjectionDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistWithSongProjection {
    private String title;
    private String id;
    private List<SongProjectionDto> songs;
}
