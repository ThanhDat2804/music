package com.music.musicservice.service;

import com.music.musicservice.model.Year;

public interface YearService {

    Year create(Integer year);
    Year getById(Integer year);
}
