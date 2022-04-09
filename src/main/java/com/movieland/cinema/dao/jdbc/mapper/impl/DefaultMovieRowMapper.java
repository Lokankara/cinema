package com.movieland.cinema.dao.jdbc.mapper.impl;

import com.movieland.cinema.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultMovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Movie movie = new Movie();

        long movieId = resultSet.getLong("movie_id");
        String description = resultSet.getString("description");
        String name_native = resultSet.getString("name_native");
        String name_translate = resultSet.getString("name_translate");
        String picture_path = resultSet.getString("picture_path");
        String year_of_release = resultSet.getString("year_of_release");
        double rating = resultSet.getDouble("rating");
        double price = resultSet.getDouble("price");

        movie.setMovieId(movieId);
        movie.setDescription(description);
        movie.setNameNative(name_native);
        movie.setNameTranslate(name_translate);
        movie.setPicturePath(picture_path);
        movie.setYearOfRelease(year_of_release);
        movie.setRating(rating);
        movie.setPrice(price);

        return movie;
    }
}
