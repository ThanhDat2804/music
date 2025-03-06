package com.music.musicservice.model.projection;

import java.time.LocalDate;

public interface UserProjection {


    String getId();

    String getName();

    LocalDate getDob();

    String getGender();

    String getLanguage();

    String getCountryIso2();
}
