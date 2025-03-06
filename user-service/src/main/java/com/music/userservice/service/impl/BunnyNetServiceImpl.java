package com.music.userservice.service.impl;


import com.music.userservice.bunny.BCDNStorage;
import com.music.userservice.service.BunnyNetService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class BunnyNetServiceImpl implements BunnyNetService {

    @Value("${bunny-net.storagePrimaryUrl}")
    private String storagePrimaryUrl;
    @Value("${bunny-net.storageZone}")
    private String storageZone;
    @Value("${bunny-net.storageUrlApiKey}")
    private String storageUrlApiKey;

    private BCDNStorage bcdnStorage;


    @PostConstruct
    public void init(){
        bcdnStorage = new BCDNStorage(storageZone,storageUrlApiKey,storagePrimaryUrl);
    }


    @Override
    public String uploadProfilePicture(MultipartFile file, String fileName, String requestKey) throws Exception {

        String key = String.format("profiles/%s",fileName);
        if(StringUtils.isNotEmpty(requestKey)){
            key = requestKey;
        }

        byte[] bytes = file.getBytes();
        bcdnStorage.uploadObject(new ByteArrayInputStream(bytes),key);

        return key;

    }



}

