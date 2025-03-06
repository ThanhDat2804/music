package com.music.musicservice.api;

import com.music.musicservice.dto.GenreDto;
import com.music.musicservice.model.Genre;
import com.music.musicservice.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreApi {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Genre genre){

        genreService.create(genre);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody GenreDto genre,
                                    @PathVariable String id){

        genreService.update(id, genre);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{genreId}/artist/{artistId}/assign-artist")
    public void addArtistToGenre(@PathVariable String genreId,
                                 @PathVariable String artistId) {

        genreService.addArtistToGenre(genreId,artistId);
    }

    @PutMapping("/{genreId}/song/{songId}/assign-song")
    public void addSongToGenre(@PathVariable String genreId,
                               @PathVariable String songId) {

        genreService.addSongToGenre(genreId,songId);
    }

    @DeleteMapping("/{genreId}/artist/{artistId}/unassign-artist")
    public void removeArtistFromGenre(@PathVariable String genreId,
                                      @PathVariable String artistId) {

        genreService.removeArtistFromGenre(genreId,artistId);
    }

    @DeleteMapping("/{genreId}/song/{songId}/un-assign-song")
    public void removeSongFromGenre(@PathVariable String genreId,
                                    @PathVariable String songId) {

        genreService.removeSongFromGenre(genreId,songId);
    }

}
