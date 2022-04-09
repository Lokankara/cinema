package com.movieland.cinema.dao.jdbc;

import com.movieland.cinema.dao.GenreDao;
import com.movieland.cinema.dao.jdbc.mapper.impl.GenreRowMapper;
import com.movieland.cinema.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcGenreDao implements GenreDao {
    private final JdbcTemplate jdbcTemplate;

    private final String SELECT_GENRES_SQL = """
            SELECT genre_id, name FROM genre;
            """;

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(
                SELECT_GENRES_SQL,
                new GenreRowMapper());
    }

}
