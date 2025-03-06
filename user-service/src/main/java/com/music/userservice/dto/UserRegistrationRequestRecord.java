package com.music.userservice.dto;

import java.time.LocalDate;

public record UserRegistrationRequestRecord(
        boolean artist,
        String id,
        String name,
        LocalDate dob,
        String gender,
        String language,
        String countryIso2
) {
}
