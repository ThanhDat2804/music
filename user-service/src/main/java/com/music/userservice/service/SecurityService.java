package com.music.userservice.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {


    public  String getUserToken(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return String.format("Bearer %s",jwt.getTokenValue());
    }

    public  String getUserId(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return jwt.getSubject();
    }
}
