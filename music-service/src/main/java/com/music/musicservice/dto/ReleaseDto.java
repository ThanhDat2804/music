package com.music.musicservice.dto;

import com.music.musicservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseDto {
    private String artistId;
    private Status status;
    private LocalDateTime releaseDate;
}
