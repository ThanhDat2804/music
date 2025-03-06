package com.music.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class User {

    @Id
    private String id;
    private LocalDate dob;
    private String countryIso2;
    private String type;
    private String storageId;
    @Enumerated(EnumType.STRING)
    private StorageProvider storageProvider;
    private String language;
    private String gender;

}

