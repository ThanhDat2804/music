package com.music.musicservice.api;

import com.music.musicservice.dto.UserRegistrationRequestRecord;
import com.music.musicservice.dto.UserUpdateRequestRecord;
import com.music.musicservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRegistrationRequestRecord> createNewUser(@RequestBody UserRegistrationRequestRecord requestRecord){

        userService.createUser(requestRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserRegistrationRequestRecord> updateUser(@PathVariable String userId,
                                                                    @RequestBody UserUpdateRequestRecord requestRecord){

        userService.update(userId, requestRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}/artist/{artistId}/follow")
    public void followArtist(@PathVariable String userId,
                             @PathVariable String artistId){
        userService.userFollowArtist(userId,artistId);
    }

    @DeleteMapping("/{userId}/artist/{artistId}/unfollow")
    public void unfollowArtist(@PathVariable String userId,
                               @PathVariable String artistId){
        userService.userUnFollowArtist(userId,artistId);
    }
}
