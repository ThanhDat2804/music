package com.music.musicservice.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BunnyNetVideoDTO {

    private Long videoLibraryId;
    public String guid;
    public String title;
    public Date dateUploaded;
    public Long views;
    public boolean isPublic;
    public Long length;
    public Long status;
    public Long framerate;
    public Long width;
    public Long height;
    public Long availableResolutions;
    public Long thumbnailCount;
    public Long encodeProgress;
    public Long storageSize;
    public boolean hasMP4Fallback;
    public String thumbnailFileName;
    public Long collectionId;
    public Long averageWatchTime;
    public Long totalWatchTime;
    public String category;
}

