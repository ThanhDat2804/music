package com.music.musicservice.api;

import com.music.musicservice.dto.SongDto;
import com.music.musicservice.dto.SongProjectionDto;
import com.music.musicservice.dto.SongRecord;
import com.music.musicservice.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongApi {

    private final SongService songService;

    @PostMapping("/artist/{artistId}")
    public ResponseEntity<SongRecord> createNewSong(@RequestBody SongRecord requestRecord,
                                                    @PathVariable String artistId){

        songService.create(requestRecord,artistId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongRecord> updateSong(@RequestBody SongDto requestRecord,
                                                    @PathVariable String id){

        songService.update(id, requestRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{songId}/user/{userId}/like")
    public void userLikeASong(@PathVariable String songId,
                               @PathVariable String userId){
        songService.userLikeASong(songId,userId);
    }


    @DeleteMapping("/{songId}/user/{userId}/dislike")
    public void userDisLikeASong(@PathVariable String songId,
                                  @PathVariable String userId){
        songService.userDisLikeASong(songId,userId);
    }

    @DeleteMapping("/{songId}/user/{userId}/play")
    public void userPlaysASong(@PathVariable String songId,
                                  @PathVariable String userId){
        songService.userPlaysASong(songId,userId);
    }

    @GetMapping("/artist/{artistId}/songs")
    public List<SongProjectionDto> userPlaysASong(@PathVariable String artistId,
                                                  @RequestParam Integer page,
                                                  @RequestParam Integer pageSize){
       return songService.getArtistSongs(artistId, page, pageSize);
    }
}
