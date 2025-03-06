package com.music.musicservice.dto;

import com.music.musicservice.model.SongType;
import com.music.musicservice.model.Status;
import com.music.musicservice.model.StorageType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongProjectionDto {
    private String id;
    private String artistId;
    private String description;
    private String name;
    private Status status;
    private Long duration;
    private String storageId;
    private StorageType storageType;
    private LocalDateTime releasedDate;
    private SongType type;
}
