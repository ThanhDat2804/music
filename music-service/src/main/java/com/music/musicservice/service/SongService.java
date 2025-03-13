package com.music.musicservice.service;

import com.music.musicservice.dto.*;
import com.music.musicservice.model.Song;
import com.music.musicservice.model.Status;

import java.util.List;

public interface SongService {

    Song create(SongRecord songRecord, String artistId);

    Song update(String id, SongDto songRecord);

    void deleteById(String id);

    void userLikeASong(String songId, String userId);

    void userDisLikeASong(String songId, String userId);

    void userPlaysASong(String songId, String userId);

    List<SongProjectionDto> getArtistSongs(String artistId, Integer page, Integer pageSize);

    PublishResponseDto updateStatus(String id, Status status);

    PublishResponseDto releaseSong(String id, ReleaseDto releaseDto);

    SongDto getSong(String id);

    SongDto getSongByStorageId(String storageId);

    BunnyVideoCallbackReq updateSongStatus(BunnyVideoCallbackReq req);
}
