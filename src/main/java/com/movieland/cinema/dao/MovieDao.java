package com.movieland.cinema.dao;

import com.movieland.cinema.domain.Movie;

import java.util.Optional;

public interface MovieDao {

    Iterable<Movie> findAll();

    Iterable<Movie> getRandomInt(int max);

    Optional<Movie> findByName(String name);

    Optional<Movie> findById(Long id);

    Iterable<Movie> findAllByGenres(Long id);
}
