package com.music.musicservice.api;

import com.music.musicservice.dto.PlaylistDto;
import com.music.musicservice.model.Album;
import com.music.musicservice.model.Playlist;
import com.music.musicservice.model.projection.PlaylistWithSongProjection;
import com.music.musicservice.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistApi {

    private final PlaylistService playlistService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Album> createNewAPlaylist(@RequestBody Playlist requestRecord,
                                                    @PathVariable String userId){
        playlistService.create(requestRecord,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{playlistId}")
    public ResponseEntity<Playlist> updatePlaylist(@RequestBody PlaylistDto requestRecord,
                                                   @PathVariable String playlistId){
        playlistService.update(playlistId, requestRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/song/{songId}/add-song")
    public void addSongIntoPlaylist(@PathVariable String id,
                                     @PathVariable String songId){
        playlistService.addSongIntoPlaylist(id,songId);
    }
    @DeleteMapping("/{id}/song/{songId}/remove-song")
    public void removeSongFromPlaylist(@PathVariable String id,
                                        @PathVariable String songId){
        playlistService.removeSongFromPlaylist(id,songId);
    }

    @GetMapping("/user/{userId}/user-playlist")
    public List<PlaylistWithSongProjection> getPlayerListByUserId(@PathVariable String userId){
        return playlistService.getPlaylistsByUserId(userId);
    }


}
