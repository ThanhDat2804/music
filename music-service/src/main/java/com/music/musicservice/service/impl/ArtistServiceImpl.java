package com.music.musicservice.service.impl;

import com.music.musicservice.model.Artist;
import com.music.musicservice.model.projection.ArtistProjection;
import com.music.musicservice.repository.ArtistRepository;
import com.music.musicservice.service.ArtistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    @Override
    public Artist createNew(Artist artist) {

        Artist artist1 = findById(artist.getId());
        if(nonNull(artist1)){
            throw  new RuntimeException(String.format("Artist with id %s already exists",artist.getId())); // todo add custom exception

        }
        Artist artist2 = Artist.builder()
                .id(artist.getId())
                .name(artist.getName())
                .build();
        return artistRepository.save(artist2);
    }

    @Override
    public Artist findById(String id) {

        ArtistProjection artistProjection = artistRepository.findByIdProjection(id).orElse(null);
        if(isNull(artistProjection)){
            return null;
        }
        return Artist.builder().id(artistProjection.getId()).name(artistProjection.getName()).build();
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void addArtistAndYearRelationship(String artistId, Integer releasedYear, String songId,String genreId) {

        artistRepository.addArtistAndYearRelationship(artistId,releasedYear,songId, LocalDateTime.now(),genreId);
    }
}
