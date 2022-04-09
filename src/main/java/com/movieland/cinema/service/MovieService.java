package com.movieland.cinema.service;

import com.movieland.cinema.entity.Movie;
import com.movieland.cinema.entity.dto.MovieDto;

import java.util.Optional;

public interface MovieService {

    Iterable<Movie> getAll();

    Iterable<Movie> getRandom();

    Optional<Movie> getByName(String name);

    Iterable<Movie> getByGenreId(Long id);

    Optional<Movie> getById(Long id);
}
