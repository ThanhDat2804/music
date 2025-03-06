package com.music.musicservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongCommentProjectionDto {
    private String id;
    private String artistId;
    private boolean edited;
    private LocalDateTime createdAt;
    private String userId;
    private String name;
}
