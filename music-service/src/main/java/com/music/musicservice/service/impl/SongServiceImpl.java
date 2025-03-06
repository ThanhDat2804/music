package com.music.musicservice.service.impl;

import com.music.musicservice.dto.*;
import com.music.musicservice.model.Song;
import com.music.musicservice.model.Status;
import com.music.musicservice.repository.SongRepository;
import com.music.musicservice.service.ArtistService;
import com.music.musicservice.service.SongService;
import com.music.musicservice.service.YearService;
import com.music.musicservice.utils.JsonUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.music.musicservice.repository.SongRepository.GET_ARTIST_SONGS;


@Service
@Slf4j
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository repository;
    private final YearService yearService;
    private final ArtistService artistService;
    private final Neo4jClient neo4jClient;
    @Override
    public Song create(SongRecord songRecord, String artistId) {


        Song song = Song.builder()
                .name(songRecord.title())
                .storageId(songRecord.storageId())
                .storageType(songRecord.storageType())
                .type(songRecord.songType())
                .duration(songRecord.duration())
                .releasedDate(songRecord.releasedDate())
                .status(Status.DRAFT)
                .build();

        Song saved = repository.save(song);
        yearService.create(songRecord.releaseYear());
        artistService.addArtistAndYearRelationship(artistId,songRecord.releaseYear(),
                saved.getId(),songRecord.genreId());

        return saved;
    }

    @Override
    public Song update(String id, SongDto songRecord) {
        yearService.create(songRecord.getReleasedDate().getYear());
        repository.removeSongReleasedYear(id, songRecord.getReleasedDate().getYear());

        Song song = repository.updateSong(
                id,
                songRecord.getDescription(),
                songRecord.getName(),
                songRecord.getReleasedDate(),
                songRecord.getType(),
                songRecord.getReleasedDate().getYear()
        );

        return song;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void userLikeASong(String songId, String userId) {

        repository.userLikeASong(songId,userId, LocalDateTime.now());

    }

    @Override
    public void userDisLikeASong(String songId, String userId) {
        repository.userDisLikeASong(songId,userId);

    }

    @Override
    public void userPlaysASong(String songId, String userId) {
        repository.userPlaysASong(songId,userId,LocalDateTime.now(),1);
    }

    @Override
    public List<SongProjectionDto> getArtistSongs(String artistId, Integer page, Integer pageSize) {
        neo4jClient.query(GET_ARTIST_SONGS)
                .bind(artistId).to("artistId")
                .bind(page).to("page")
                .bind(pageSize).to("pageSize")
                .fetch()
                .all()
                .stream()
                .map(map -> JsonUtility.fromMap(map, SongProjectionDto.class))
                .collect(Collectors.toList());
        return List.of();
    }

    @Override
    public PublishResponseDto updateStatus(String id, Status status) {
        repository.updateSongStatus(id, status);
        return PublishResponseDto.builder()
                .isSuccess(true)
                .id(id)
                .build();
    }

    @Override
    public PublishResponseDto releaseSong(String id, ReleaseDto releaseDto) {

        if(!Objects.equals(Status.SCHEDULED, releaseDto.getStatus())){
            //TODO: call task scheduler to create a task
            LocalDateTime releaseDate = releaseDto.getReleaseDate();

        }
        return updateStatus(id, Status.PUBLISHED);
    }
}
