package com.music.auth.service.service.impl;

import com.music.auth.service.dto.LoginResquest;
import com.music.auth.service.dto.UserRegistrationRecord;
import com.music.auth.service.model.Role;
import com.music.auth.service.service.KeycloakUserService;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class KeycloakUserServiceImpl implements KeycloakUserService {

    private static final String UPDATE_PASSWORD = "UPDATE_PASSWORD";
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.client}")
    private String clientId;
    @Value("${keycloak.adminClientSecret}")
    private String clientSecret;
    @Value("${keycloak.urls.auth}")
    private String authUrl;
    private Keycloak keycloak;

    public KeycloakUserServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) {

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userRegistrationRecord.password());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(user);

        if (Objects.equals(201, response.getStatus())) {

            List<UserRepresentation> representationList = usersResource.searchByUsername(userRegistrationRecord.username(), true);
            if (!CollectionUtils.isEmpty(representationList)) {
                UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
                assert userRepresentation1 != null;
                emailVerification(userRepresentation1.getId());
                if (userRegistrationRecord.artist()) {
                    assignRoleToUser(userRepresentation1.getId(), Role.ARTIST);
                    assignRoleToUser(userRepresentation1.getId(), Role.LISTENER);
                } else {
                    assignRoleToUser(userRepresentation1.getId(), Role.LISTENER);
                }

            }
            return userRegistrationRecord;
        }

//        response.readEntity()

        return null;
    }

    @Override
    public UserRepresentation getUserById(String userId) {


        return getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {

        getUsersResource().delete(userId);
    }


    @Override
    public void emailVerification(String userId) {

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public void forgotPassword(String username) {

        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> representationList = usersResource.searchByUsername(username, true);

        UserRepresentation userRepresentation = representationList.stream().findFirst().orElse(null);

        if (userRepresentation != null) {
            UserResource userResource = usersResource.get(userRepresentation.getId());
            List<String> actions = new ArrayList<>();
            actions.add(UPDATE_PASSWORD);
            userResource.executeActionsEmail(actions);
            return;
        }
        throw new RuntimeException("Username not found");
    }

    @Override
    public String login(LoginResquest loginRequest) {
        try {
            Keycloak keycloak = Keycloak.getInstance(
                    authUrl,
                    realm,
                    loginRequest.username(),
                    loginRequest.password(),
                    clientId,
                    clientSecret
            );

            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
            return tokenResponse.getToken();
        } catch (Exception e) {
            log.error("Invalid username or password", e);
            throw new RuntimeException("Invalid username or password");
        }
    }

    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    private RoleRepresentation getRole(String role) {
        RolesResource rolesResource = getRolesResource();
        return rolesResource.get(role).toRepresentation();
    }

    private void assignRoleToUser(String userId, String role) {
        UsersResource usersResource = getUsersResource();
        UserResource userResource = usersResource.get(userId);
        RoleRepresentation roleRepresentation = getRole(role);
        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));
    }

    private RolesResource getRolesResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.roles();
    }
}
