package com.music.musicservice.dto;

import java.time.LocalDate;

public record UserUpdateRequestRecord(

        String id,

        String name,

        LocalDate dob,

        String gender,

        String language,

        String countryIso2) {
}
