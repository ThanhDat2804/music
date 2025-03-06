package com.music.auth.service.dto;

public record UserRegistrationRecord(String username, String email, String firstName, String lastName, String password, boolean artist) {
}
