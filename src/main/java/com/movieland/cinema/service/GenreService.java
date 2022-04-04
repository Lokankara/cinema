package com.movieland.cinema.service;

import com.movieland.cinema.domain.Genre;

import java.util.Optional;

public interface GenreService {

    Iterable<Genre> getAll();

    Optional<Genre> findById(Long genre_id);
}
