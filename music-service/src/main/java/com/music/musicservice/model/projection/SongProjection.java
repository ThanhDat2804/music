package com.music.musicservice.model.projection;


import com.music.musicservice.model.Year;

public interface SongProjection {
    String getId();
    String getDescription();
    String getName();
    String getStatus();
    String getDuration();
    String getStorageId();
    String getStorageType();
    String getType();
   // Year getYear();
    Integer getYear();
    String getArtistId();

}
