package com.music.auth.service.api;

import com.music.auth.service.dto.LoginResquest;
import com.music.auth.service.service.KeycloakUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class PublicApi {

    private final KeycloakUserService keycloakUserService;

    @PutMapping("/{username}/forgot-password")
    public void forgotPassword(@PathVariable String username) {
        keycloakUserService.forgotPassword(username);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginResquest loginRequest) {
        return keycloakUserService.login(loginRequest);
    }
}
