package com.movieland.cinema.dao.jdbc;

import com.movieland.cinema.dao.MovieDao;
import com.movieland.cinema.dao.jdbc.mapper.impl.DefaultMovieRowMapper;
import com.movieland.cinema.dao.jdbc.mapper.impl.MovieResultSetExtractor;
import com.movieland.cinema.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Collections;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {

    private final String selectAll;
    private final String selectById;
    private final String selectByName;
    private final String selectRandom;
    private final String selectAllByGenre;
    private final DefaultMovieRowMapper MOVIE_ROW_MAPPER
            = new DefaultMovieRowMapper();
    private static final MovieResultSetExtractor RESULT_SET_EXTRACTOR
            = new MovieResultSetExtractor();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.parameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public Iterable<Movie> findAll(String sql) {
        return jdbcTemplate.query(
                sql,
                MOVIE_ROW_MAPPER);
    }

    @Override
    public Optional<Movie> findByName(String name) {

        Movie movie = new Movie();
        movie.setNameTranslate(name);
        return Optional.ofNullable(
                parameterJdbcTemplate.queryForObject(
                        selectByName,
                        new BeanPropertySqlParameterSource(movie),
                        MOVIE_ROW_MAPPER));
    }

    @Override
    public Iterable<Movie> findAll() {
        return findAll(selectAll);
    }

    @Override
    public Iterable<Movie> getRandom() {
        return parameterJdbcTemplate.query(
                selectRandom,
                Collections.singletonMap("size", 3),
                MOVIE_ROW_MAPPER);
    }

    @Override
    public Iterable<Movie> findMoviesByGenre(Long id) {
        return jdbcTemplate.query(
                selectAllByGenre,
                new Object[]{id},
                new int[]{Types.INTEGER},
                MOVIE_ROW_MAPPER);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        Movie query = jdbcTemplate.query(
                selectById,
                RESULT_SET_EXTRACTOR, id);
        return Optional.ofNullable(query);
    }
}