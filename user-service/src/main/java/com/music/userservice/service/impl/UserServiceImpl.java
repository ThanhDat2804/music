package com.music.userservice.service.impl;

import com.music.userservice.dto.KeycloakUser;
import com.music.userservice.dto.UserRegistrationRequestRecord;
import com.music.userservice.dto.UserUpdateRecord;
import com.music.userservice.model.StorageProvider;
import com.music.userservice.model.User;
import com.music.userservice.proxy.AuthProxy;
import com.music.userservice.proxy.MusicProxy;
import com.music.userservice.repository.UserRepository;
import com.music.userservice.service.BunnyNetService;
import com.music.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BunnyNetService bunnyNetService;
    private final MusicProxy musicProxy;
    private final AuthProxy authProxy;
    @Override
    public User save(User user) {

        User save = userRepository.save(user);
        createMusicUser(save);
        return save;
    }

    private void createMusicUser(User user) {

        KeycloakUser keycloakUser = authProxy.getUserById(user.getId());
        boolean artist = Objects.equals("ARTIST",user.getType());

        UserRegistrationRequestRecord userRegistrationRequestRecord = new UserRegistrationRequestRecord(
                artist,user.getId(),String.format("%s %s",keycloakUser.getFirstName(),keycloakUser.getLastName()),user.getDob(),user.getGender(),user.getLanguage(),user.getCountryIso2()
        );
        musicProxy.createNewUser(userRegistrationRequestRecord);

    }

    @Override
    public User update(User user, String userId) {

        User user1 = getById(userId);
        if(nonNull(user1)){
            user.setId(userId);
            updateMusicUser(user);
            return  userRepository.save(user);
        }
        throw new RuntimeException("USer does not exist for id "+userId);
    }

    private void updateMusicUser(User user) {

        KeycloakUser keycloakUser = authProxy.getUserById(user.getId());
        UserUpdateRecord userUpdateRecord=new UserUpdateRecord(String.format("%s %s",keycloakUser.getFirstName(),keycloakUser.getLastName()),user.getDob(),user.getGender(),user.getLanguage(),user.getCountryIso2());
        musicProxy.updateUser(userUpdateRecord,user.getId());
    }

    @Override
    public User getById(String userId) {

        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);

    }

    @Override
    public void uploadProfilePicture(String userId, String key, MultipartFile file, StorageProvider storageProvider) throws Exception {

        User user = getById(userId);
        String savedKey =bunnyNetService.uploadProfilePicture(file,null,key);
        user.setStorageProvider(storageProvider);
        user.setStorageId(savedKey);
        userRepository.save(user);


    }
}
