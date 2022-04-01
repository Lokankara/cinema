package com.movieland.cinema.dao.jdbc.config;

import com.movieland.cinema.domain.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Movie movie = new Movie();
        movie.setMovie_id(resultSet.getLong("movie_id"));
        movie.setNameRussian(resultSet.getString("nameRussian"));
        movie.setNameNative(resultSet.getString("nameNative"));
        movie.setYearOfRelease(resultSet.getString("yearOfRelease"));
        movie.setPrice(resultSet.getDouble("price"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setDescription(resultSet.getString("description"));

        return movie;
    }
}
