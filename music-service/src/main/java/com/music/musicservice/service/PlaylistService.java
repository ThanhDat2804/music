package com.music.musicservice.service;

import com.music.musicservice.dto.PlaylistDto;
import com.music.musicservice.model.Playlist;
import com.music.musicservice.model.projection.PlaylistWithSongProjection;

import java.util.List;

public interface PlaylistService {

    Playlist create(Playlist playlist, String userId);

    Playlist update(String id, PlaylistDto playlist);

    void deleteById(String id);

    void addSongIntoPlaylist(String playlistId, String songId);

    void removeSongFromPlaylist(String playlistId, String songId);

    List<PlaylistWithSongProjection> getPlaylistsByUserId(String userId);
}
