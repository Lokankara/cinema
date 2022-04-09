package com.movieland.cinema.service;

import com.movieland.cinema.entity.Country;

import java.util.Optional;

public interface CountryService {

    Optional<Country> getByName(String username);
}
