package com.movieland.cinema.dao.jdbc.mapper;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@AllArgsConstructor
public class MovieFullRowMapper implements RowMapper<Movie> {

    private MovieRowMapper mapper;
    private GenreRowMapper genreMapper;

    @Override
    public Movie mapRow(@NonNull ResultSet resultSet, int row) throws SQLException {

        Movie movie = mapper.mapRow(resultSet, row);
        Genre genre = genreMapper.mapRow(resultSet, row);

        Set<Genre> genres = new HashSet<>();
        genres.add(genre);
        if (movie != null) {
            movie.setGenres(genres);
        }
        return movie;
    }
}