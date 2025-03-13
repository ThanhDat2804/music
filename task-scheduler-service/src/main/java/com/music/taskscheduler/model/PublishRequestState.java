package com.music.taskscheduler.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishRequestState {

    private String songId;
    private String albumId;
    private String message;
    private String success;
}

