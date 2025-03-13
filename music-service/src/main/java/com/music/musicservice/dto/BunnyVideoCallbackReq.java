package com.music.musicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BunnyVideoCallbackReq {

    private Long VideoLibraryId;
    private String VideoGuid;
    private Integer Status;
}
