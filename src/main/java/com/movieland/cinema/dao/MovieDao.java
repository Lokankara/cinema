package com.movieland.cinema.dao;

import com.movieland.cinema.domain.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findAll();
}
