package com.music.musicservice.api;

import com.music.musicservice.dto.AlbumDto;
import com.music.musicservice.model.Album;
import com.music.musicservice.model.projection.AlbumWithSongProjection;
import com.music.musicservice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumApi {

    private final AlbumService albumService;

    @PostMapping("/artist/{artistId}")
    public ResponseEntity<Album> createNewAlbum(@RequestBody Album requestRecord,
                                                @PathVariable String artistId,
                                                @RequestParam Integer releasedYear) {

        albumService.create(requestRecord,releasedYear,artistId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{artistId}")
    public ResponseEntity<Album> updateAlbum(@RequestBody String albumId ,
                                                @RequestBody AlbumDto album) {

        albumService.update(albumId, album);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{albumId}/user/{userId}/like")
    public void userLikeAnAlbum(@PathVariable String albumId,
                                @PathVariable String userId){
        albumService.userLikeAnAlbum(albumId,userId);
    }


    @DeleteMapping("/{albumId}/user/{userId}/dislike")
    public void userDisLikeAAlbum(@PathVariable String albumId,
                                  @PathVariable String userId) {
        albumService.userDikeLikeAnAlbum(albumId,userId);
    }

    @PutMapping("/{albumId}/song/{songId}/add-to-album")
    public void addSongToAlbum(@PathVariable String albumId,
                               @PathVariable String userId) {
        albumService.userDikeLikeAnAlbum(albumId,userId);
    }

    @GetMapping("/artist/{artistId}/album-songs")
    public List<AlbumWithSongProjection> addSongToAlbum(@PathVariable String artistId) {
       return albumService.getAlbumWithSongs(artistId);
    }
}
