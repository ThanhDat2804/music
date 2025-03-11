package com.music.musicservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private String id;
    private LocalDateTime processDate;
    private Map<String, Object> data;
}
