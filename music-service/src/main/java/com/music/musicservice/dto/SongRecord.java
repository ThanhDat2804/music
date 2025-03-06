package com.music.musicservice.dto;

import com.music.musicservice.model.SongType;
import com.music.musicservice.model.StorageType;

import java.time.LocalDateTime;

public record SongRecord (String title,
                          String storageId,
                          StorageType storageType,
                          SongType songType,
                          String albumId,
                          String genreId,
                          long duration,
                          LocalDateTime releasedDate,
                          Integer releaseYear) {
}
