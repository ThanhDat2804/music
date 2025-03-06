package com.music.musicservice.service;

import com.music.musicservice.dto.UserRegistrationRequestRecord;
import com.music.musicservice.dto.UserUpdateRequestRecord;
import com.music.musicservice.model.User;

public interface UserService {

    User createUser(UserRegistrationRequestRecord user);

    User update(String id, UserUpdateRequestRecord user);

    void deleteByUserId(String id);

    void userFollowArtist(String userId,String artistId);


    void userUnFollowArtist(String userId,String artistId);

    boolean isUserFollowingArtist(String userId,String songId);

    void userInterestWithArtistBySongId(String userId, String songId);

    boolean isUserInterestWithTheArtist(String userId, String songId);
}
