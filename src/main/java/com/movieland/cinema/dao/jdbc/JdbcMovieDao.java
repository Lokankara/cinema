package com.movieland.cinema.dao.jdbc;

import com.movieland.cinema.dao.MovieDao;
import com.movieland.cinema.dao.jdbc.config.MovieRowMapper;
import com.movieland.cinema.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Movie> findAll() {
        return this.jdbcTemplate.query( "select movie_id, nameNative, nameRussian, yearOfRelease, rating, price, description from Movie", new MovieRowMapper());
    }
}