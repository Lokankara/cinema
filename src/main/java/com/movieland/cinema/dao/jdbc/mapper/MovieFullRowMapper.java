package com.movieland.cinema.dao.jdbc.mapper;

import com.movieland.cinema.domain.Country;
import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.domain.Review;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@AllArgsConstructor
public class MovieFullRowMapper implements RowMapper<Movie> {

    private final MovieRowMapper movieRowMapper;
    private final GenreRowMapper genreRowMapper;
    private final CountryRowMapper countryRowMapper;
    private final ReviewRowMapper reviewRowMapper;

    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        int index = 0;
        Set<Genre> genres = new HashSet<>();
        List<Country> countries = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        Movie movie = movieRowMapper.mapRow(resultSet, index);

        while (resultSet.next()) {
            Genre genre = genreRowMapper.mapRow(resultSet, index);
            Country country = countryRowMapper.mapRow(resultSet, index);
            Review review = reviewRowMapper.mapRow(resultSet, index);

            if (!genres.contains(genre)) {
                genres.add(genre);
            }
            if (!countries.contains(country)) {
                countries.add(country);
            }
            if (!reviews.contains(review)) {
                reviews.add(review);
            }
        }

        Objects.requireNonNull(movie).setGenres(genres);
        movie.setCountries(countries);
        movie.setReviews(reviews);

        return movie;
    }
}