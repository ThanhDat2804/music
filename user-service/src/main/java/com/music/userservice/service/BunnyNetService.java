package com.music.userservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface BunnyNetService {

    String uploadProfilePicture(MultipartFile file, String fileName, String requestKey) throws Exception;
}

