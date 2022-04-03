package com.movieland.cinema.dao.jdbc;

import com.movieland.cinema.dao.MovieDao;
import com.movieland.cinema.dao.jdbc.mapper.MovieRowMapper;
import com.movieland.cinema.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.parameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    private final String SELECT_All_BY_GENRE_SQL = """
            SELECT movie_id, name_native, name_translate, year_of_release, rating, price, description, picture_path
            FROM movie
            INNER_JOIN poster WHERE movie_id :id;
            """;

    private final String SELECT_BY_NAME_TRASLATE_SQL = """
            SELECT movie_id, name_native, name_translate, year_of_release, rating, price, description, picture_path
            FROM movie WHERE movie_id :id;
            """;

    private final String SELECT_RANDOM_SQL = """
            SELECT movie_id, name_native, name_translate, year_of_release, rating, price, description, picture_path
            FROM movie
            ORDER BY random() LIMIT :max;
            """;
    private final String SELECT_MOVIES_SQL =
            "SELECT movie_id, name_native, name_translate, year_of_release, rating, price, description, picture_path FROM movie";


    @Override
    public Iterable<Movie> findAll(String sql) {
        return jdbcTemplate.query(
                sql,
                new MovieRowMapper());
    }

    @Override
    public Optional<Movie> findByName(String name) {
        Iterable<Movie> movies = findAll(SELECT_BY_NAME_TRASLATE_SQL);
        for (Movie movie : movies) {
            if (movie.getNameTranslate().equals(name)) {
                return Optional.of(movie);
            }
        }
        return Optional.empty();
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
    public Iterable<Movie> findById(Long id, String sql) {
        return parameterJdbcTemplate.query(
                sql,
                Collections.singletonMap("id", id),
                new MovieRowMapper());
    }

    @Override
    public Iterable<Movie> findAllByGenres(Long id) {
        return findById(id, SELECT_All_BY_GENRE_SQL);
    }
}
