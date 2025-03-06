package com.music.musicservice.service;

import com.music.musicservice.dto.GenreDto;
import com.music.musicservice.model.Genre;

public interface GenreService {
    Genre create(Genre genre);
    Genre update(String id, GenreDto genre);
    void addArtistToGenre(String genreId, String artistId);
    void removeArtistFromGenre(String genreId, String artistId);
    void addSongToGenre(String genreId, String songId);
    void removeSongFromGenre(String genreId, String songId);
}
