package com.movieland.cinema.dao.jdbc.mapper;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Genre genre = new Genre();
        genre.setGenreId(resultSet.getLong("genre_id"));
        genre.setName(resultSet.getString("name"));
        while (resultSet.next()) {
            Movie movie = new Movie();
            movie.setMovieId(resultSet.getLong(1));
            movie.setNameTranslate(resultSet.getString("name"));
        }
        return genre;
    }
}
