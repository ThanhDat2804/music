package com.music.musicservice.dto;

import com.music.musicservice.model.SongType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {

    private String description;

    private String name;

    private LocalDateTime releasedDate;

    private SongType type;

    private String albumId;

}
