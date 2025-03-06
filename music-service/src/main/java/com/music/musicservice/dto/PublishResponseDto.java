package com.music.musicservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishResponseDto {
    private String id;
    private boolean isSuccess;

}
