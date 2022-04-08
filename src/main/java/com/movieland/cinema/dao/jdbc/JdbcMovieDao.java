package com.movieland.cinema.dao.jdbc;

import com.movieland.cinema.dao.MovieDao;
import com.movieland.cinema.dao.jdbc.mapper.*;
import com.movieland.cinema.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    private final String SELECT_MOVIE_SQL =
            "SELECT m.movie_id, m.name_native, m.name_translate, m.year_of_release, m.rating, m.price, m.description, m.picture_path ";


    private final String SELECT_BY_ID_SQL = """
            SELECT mg.movie_id, m.name_translate, m.name_native, m.year_of_release, m.description,
            m.rating, m.price, m.picture_path, mc.country_id, c.country_name,
            mg.genre_id, g.name, r.review_id, r.user_id, r.text
            FROM movie m
            LEFT JOIN movie_countries AS mc ON (m.movie_id = mc.movie_id)
            LEFT JOIN country AS c ON (mc.country_id = c.country_id)
            LEFT JOIN movie_genre AS mg ON (mc.movie_id = mg.movie_id)
            LEFT JOIN genre AS g ON (mg.genre_id = g.genre_id)
            LEFT JOIN review AS r ON (mg.movie_id = r.movie_id)
            WHERE m.movie_id = ?;
              """;

    private final String SELECT_All_BY_GENRE_SQL =
            SELECT_MOVIE_SQL + """
                    FROM movie m
                    LEFT JOIN movie_genre AS mg ON mg.movie_id = m.movie_id WHERE mg.genre_id=?;
                    """;

    private final String SELECT_BY_NAME_TRANSLATE_SQL =
            SELECT_MOVIE_SQL + """
                    FROM movie m
                    WHERE name_translate = :nameTranslate;
                    """;

    private final String SELECT_RANDOM_SQL =
            SELECT_MOVIE_SQL + """
                    FROM movie m
                    ORDER BY random() LIMIT :max;
                    """;

    private final String SELECT_MOVIES_SQL = SELECT_MOVIE_SQL + "FROM movie m;";

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
    public Iterable<Movie> findMoviesByGenre(Long id) {
        return jdbcTemplate.query(
                SELECT_All_BY_GENRE_SQL,
                new Object[]{id},
                new int[]{Types.INTEGER},
                new MovieRowMapper());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        List<Movie> query = jdbcTemplate.query(
                SELECT_BY_ID_SQL,
                new MovieFullRowMapper(
                        new MovieRowMapper(),
                        new GenreRowMapper(),
                        new CountryRowMapper(),
                        new ReviewRowMapper()), id);
        return Optional.ofNullable(query.get(0));
    }
}