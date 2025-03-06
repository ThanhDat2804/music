package com.music.musicservice.service;

import com.music.musicservice.model.Artist;

public interface ArtistService {

    Artist createNew(Artist artist);
    Artist findById(String id);
    void deleteById(String id);

    void addArtistAndYearRelationship(String artistId, Integer integer, String id,String genreId);
}
