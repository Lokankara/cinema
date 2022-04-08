package com.movieland.cinema.dao;

import com.movieland.cinema.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> findAll();
}
