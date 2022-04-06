package com.movieland.cinema.dao.jdbc;

import com.movieland.cinema.dao.MovieDao;
import com.movieland.cinema.dao.jdbc.mapper.GenreRowMapper;
import com.movieland.cinema.dao.jdbc.mapper.MovieFullRowMapper;
import com.movieland.cinema.dao.jdbc.mapper.MovieRowMapper;
import com.movieland.cinema.domain.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {

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

    private final String SELECT_BY_ID_SQL = """
            SELECT m.movie_id, m.name_native, m.name_translate, m.year_of_release, m.rating, m.price, m.description, m.picture_path, genre.name, genre.genre_id
            FROM movie m
            INNER JOIN movie_genre AS mg ON mg.movie_id = m.movie_id
            INNER JOIN genre ON mg.genre_id = genre.genre_id
            WHERE m.movie_id=?;
            """;

    private final String SELECT_All_BY_GENRE_SQL = """
            SELECT m.movie_id, m.name_native, m.name_translate, m.year_of_release, m.rating, m.price, m.description, m.picture_path
            FROM movie m
            LEFT JOIN movie_genre AS mg ON mg.movie_id = m.movie_id WHERE mg.genre_id=?;
            """;

    private final String SELECT_BY_NAME_TRANSLATE_SQL = """
            SELECT movie_id, name_native, name_translate, year_of_release, rating, price, description, picture_path
            FROM movie
            WHERE name_translate = :nameTranslate;
            """;

    private final String SELECT_RANDOM_SQL = """
            SELECT movie_id, name_native, name_translate, year_of_release, rating, price, description, picture_path
            FROM movie
            ORDER BY random() LIMIT :max;
            """;

    private final String SELECT_MOVIES_SQL = """
            SELECT movie_id, name_native, name_translate, year_of_release, rating, price, description, picture_path FROM movie;
            """;

    public Iterable<Movie> findAll(String sql) {
        return jdbcTemplate.query(
                sql,
                new MovieRowMapper());
    }

    @Override
    public Optional<Movie> findByName(String name) {

        Movie movie = new Movie();
        movie.setNameTranslate(name);

        return Optional.ofNullable(
                parameterJdbcTemplate.queryForObject(
                        SELECT_BY_NAME_TRANSLATE_SQL,
                        new BeanPropertySqlParameterSource(movie),
                        new MovieRowMapper()));
    }

    @Override
    public Iterable<Movie> findAll() {
        return findAll(SELECT_MOVIES_SQL);
    }

    @Override
    public Iterable<Movie> getRandomInt(int max) {
        return parameterJdbcTemplate.query(
                SELECT_RANDOM_SQL,
                Collections.singletonMap("max", max),
                new MovieRowMapper());
    }

    @Override
    public Iterable<Movie> findAllByGenres(Long id) {
        return jdbcTemplate.query(
                SELECT_All_BY_GENRE_SQL,
                new Object[]{id},
                new int[]{Types.INTEGER},
                new MovieRowMapper());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        SELECT_BY_ID_SQL,
                        new Object[]{id},
                        new int[]{Types.INTEGER},
                        new MovieFullRowMapper(
                                new MovieRowMapper(),
                                new GenreRowMapper())));
    }
}