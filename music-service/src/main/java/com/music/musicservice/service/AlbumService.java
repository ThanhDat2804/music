package com.music.musicservice.service;

import com.music.musicservice.dto.AlbumDto;
import com.music.musicservice.dto.PublishResponseDto;
import com.music.musicservice.dto.ReleaseDto;
import com.music.musicservice.dto.SongProjectionDto;
import com.music.musicservice.model.Album;
import com.music.musicservice.model.Status;
import com.music.musicservice.model.projection.AlbumProjection;
import com.music.musicservice.model.projection.AlbumWithSongProjection;

import java.util.List;

public interface AlbumService {

    Album create(Album album, Integer releasedYear, String artistId);

    Album update(String id, AlbumDto album);

    AlbumProjection getByIdProjection(String id);

    void deleteById(String id);

    void userLikeAnAlbum(String albumId,String userId);

    void userDikeLikeAnAlbum(String albumId,String userId);

    void addSongToAlbum(String albumId, String songId);

    List<AlbumWithSongProjection> getAlbumWithSongs(String artistId);

    List<SongProjectionDto> getAlbumSongs(String albumId);

    PublishResponseDto releaseAlbum(String id, ReleaseDto releaseDto);

    PublishResponseDto updateStatus(String id, Status status);
}
