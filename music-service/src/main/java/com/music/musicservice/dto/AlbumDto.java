package com.music.musicservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AlbumDto {

    private String description;
    private String name;
    private LocalDateTime releasedDate;
}
