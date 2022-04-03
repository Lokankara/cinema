package com.movieland.cinema.dao.jdbc.mapper;

import com.movieland.cinema.domain.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Movie movie = new Movie();
        movie.setMovieId(resultSet.getLong("movie_id"));
        movie.setDescription(resultSet.getString("description"));
        movie.setNameNative(resultSet.getString("name_native"));
        movie.setNameTranslate(resultSet.getString("name_translate"));
        movie.setPicturePath(resultSet.getString("picture_path"));
        movie.setYearOfRelease(resultSet.getString("year_of_release"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setPrice(resultSet.getDouble("price"));
        return movie;
    }
}
