package com.movieland.cinema.dao.jdbc.mapper.impl;

import com.movieland.cinema.entity.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Genre genre = new Genre();

        long genre_id = resultSet.getLong("genre_id");
        String name = resultSet.getString("name");

        genre.setGenreId(genre_id);
        genre.setName(name);

        return genre;
    }
}
