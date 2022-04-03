package com.movieland.cinema.dao;

import com.movieland.cinema.domain.Movie;

import java.util.Optional;

public interface MovieDao {

    Iterable<Movie> findAll();

    Iterable<Movie> getRandomInt(int max);

    Iterable<Movie> findAll(String sql);

    Optional<Movie> findByName(String name);

    Iterable<Movie> findById(Long id, String name);

    Iterable<Movie> findAllByGenres(Long id);
}
