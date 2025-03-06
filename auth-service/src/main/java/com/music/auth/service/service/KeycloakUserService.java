package com.music.auth.service.service;

import com.music.auth.service.dto.LoginResquest;
import com.music.auth.service.dto.UserRegistrationRecord;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakUserService {

    UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord);
    UserRepresentation getUserById(String userId);
    void deleteUserById(String userId);
    void emailVerification(String userId);
    void forgotPassword(String username);
    String login(LoginResquest loginRequest);
}
