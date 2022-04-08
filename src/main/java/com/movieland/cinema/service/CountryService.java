package com.movieland.cinema.service;

import com.movieland.cinema.domain.Country;

import java.util.Optional;

public interface CountryService {

    Optional<Country> getByName(String username);
}
