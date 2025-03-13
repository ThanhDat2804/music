package com.music.taskscheduler.model;

import com.music.taskscheduler.utils.MapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime processDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
    private String message;

    @Convert(converter = MapConverter.class)
    private Map<String, Object> data;
}
