package com.music.musicservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlaylistDto {
    private String title;
    private String description;
}
