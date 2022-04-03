package com.movieland.cinema.service;

import com.movieland.cinema.domain.Movie;

import java.util.Optional;

public interface MovieService {

    Iterable<Movie> getAll();

    Iterable<Movie> getRandom(int max);

    Optional<Movie> findByName(String name);

    Iterable<Movie> getByGenreId(Long id);
}
