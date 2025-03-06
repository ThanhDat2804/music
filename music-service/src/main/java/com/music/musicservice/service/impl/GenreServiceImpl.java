package com.music.musicservice.service.impl;

import com.music.musicservice.dto.GenreDto;
import com.music.musicservice.model.Genre;
import com.music.musicservice.repository.GenreRepository;
import com.music.musicservice.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    @Override
    public Genre create(Genre genre) {


        return genreRepository.save(Genre.builder().name(genre.getName()).key(genre.getKey()).build());
    }

    @Override
    public Genre update(String id, GenreDto genre) {
        return genreRepository.updateGenre(id ,genre.getKey(), genre.getName());
    }


    @Override
    public void addArtistToGenre(String genreId, String artistId) {

        genreRepository.addArtistToGenre(genreId,artistId, LocalDateTime.now());

    }

    @Override
    public void removeArtistFromGenre(String genreId, String artistId) {

        genreRepository.removeArtistFromGenre(genreId,artistId);
    }

    @Override
    public void addSongToGenre(String genreId, String songId) {

        genreRepository.addSongToGenre(genreId,songId,LocalDateTime.now());
    }

    @Override
    public void removeSongFromGenre(String genreId, String songId) {
        genreRepository.removeSongFromGenre(genreId,songId);
    }
}
