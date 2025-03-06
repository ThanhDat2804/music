package com.music.musicservice.service.impl;

import com.music.musicservice.dto.PlaylistDto;
import com.music.musicservice.model.Playlist;
import com.music.musicservice.model.projection.PlaylistWithSongProjection;
import com.music.musicservice.repository.PlaylistRepository;
import com.music.musicservice.service.PlaylistService;
import com.music.musicservice.utils.JsonUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.music.musicservice.repository.PlaylistRepository.GET_USER_PLAYLISTS_QUERY;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private Neo4jClient neo4jClient;

    @Override
    public Playlist create(Playlist playlist, String userId) {

        Playlist playlist1 = Playlist.builder()
                .title(playlist.getTitle())
                .description(playlist.getDescription()).build();
        Playlist saved = playlistRepository.save(playlist1);
        playlistRepository.addPlaylistAndUserRelationship(saved.getId(), userId, LocalDateTime.now());
        return saved;
    }

    @Override
    public Playlist update(String id, PlaylistDto playlist) {

        //TODO: projection find by ID
        return playlistRepository.updatePlaylist(id, playlist.getTitle(), playlist.getDescription());
    }

    @Override
    public void deleteById(String id) {

        playlistRepository.deleteById(id);

    }

    @Override
    public void addSongIntoPlaylist(String playlistId, String songId) {

        playlistRepository.addSongToPlaylist(playlistId, songId, LocalDateTime.now());

    }

    @Override
    public void removeSongFromPlaylist(String playlistId, String songId) {

        playlistRepository.removeSongFromPlaylist(playlistId, songId);

    }

    @Override
    public List<PlaylistWithSongProjection> getPlaylistsByUserId(String userId) {
        return neo4jClient.query(GET_USER_PLAYLISTS_QUERY)
                .bind(userId).to("userId")
                .fetch()
                .all()
                .stream()
                .map(record -> {

                    return JsonUtility.fromMap(record, PlaylistWithSongProjection.class);
                })
                .collect(Collectors.toList());
    }
}
