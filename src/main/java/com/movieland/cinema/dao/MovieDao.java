package com.movieland.cinema.dao;

import com.movieland.cinema.entity.Movie;

import java.util.Optional;

public interface MovieDao {

    Iterable<Movie> findAll();

    Iterable<Movie> getRandom();

    Optional<Movie> findByName(String name);

    Optional<Movie> findById(Long id);

    Iterable<Movie> findMoviesByGenre(Long id);
}
