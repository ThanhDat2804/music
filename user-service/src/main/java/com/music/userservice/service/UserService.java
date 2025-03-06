package com.music.userservice.service;

import com.music.userservice.model.StorageProvider;
import com.music.userservice.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User save(User user);
    User update(User user, String userId);
    User getById(String userId);
    void deleteUserById(String userId);
    void uploadProfilePicture(String userId, String key, MultipartFile file, StorageProvider storageProvider) throws Exception;
}
