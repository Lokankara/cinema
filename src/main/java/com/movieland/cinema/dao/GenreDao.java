package com.movieland.cinema.dao;

import com.movieland.cinema.entity.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> findAll();
}
