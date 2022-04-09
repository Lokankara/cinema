package com.movieland.cinema.dao.jdbc.mapper.impl;

import com.movieland.cinema.entity.Country;
import com.movieland.cinema.entity.Genre;
import com.movieland.cinema.entity.Movie;
import com.movieland.cinema.entity.Review;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class MovieResultSetExtractor implements ResultSetExtractor<Movie> {

    private static final DefaultMovieRowMapper MOVIE_ROW_MAPPER = new DefaultMovieRowMapper();
    private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();
    private static final ReviewRowMapper REVIEW_ROW_MAPPER = new ReviewRowMapper();
    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();

    @SneakyThrows
    @Override
    public Movie extractData(ResultSet resultSet) throws DataAccessException {

        ResultSetMetaData metaData = resultSet.getMetaData();
        int numRow = metaData.getColumnCount();
        Set<Country> countries = new HashSet<>();
        Set<Genre> genres = new HashSet<>();
        Set<Review> reviews = new HashSet<>();
        Set<Movie> movies = new HashSet<>();
        Movie movie = new Movie();

        while (resultSet.next()) {
                movie = MOVIE_ROW_MAPPER.mapRow(resultSet, 0);
            for (int i = 1; i <= numRow; i++) {
                Country country = COUNTRY_ROW_MAPPER.mapRow(resultSet, i);
                Review review = REVIEW_ROW_MAPPER.mapRow(resultSet, i);
                Genre genre = GENRE_ROW_MAPPER.mapRow(resultSet, i);

                genres.add(genre);
                countries.add(country);
                reviews.add(review);
                movies.add(movie);
            }
            Objects.requireNonNull(movie).setGenres(genres);
            Objects.requireNonNull(movie).setReviews(reviews);
            Objects.requireNonNull(movie).setCountries(countries);

        }
        System.out.println(countries);
        return movie;
    }
}

//
//                String columnName = metaData.getColumnName(i);
//                String value = resultSet.getString(columnName);
//                System.out.println(country);

//                if (map.get(columnName) != null) {
//                    values = map.get(columnName);
//                };
//                values.add(value);
//                map.put(columnName, values);
//            movie.setMovieId(resultSet.getString(1));
//            Map<String, String> row = new HashMap<>();

//                map.forEach((k, v) -> System.out.println((v + ":" + k)));

//                Field[] declaredFields = movieDto.getClass().getDeclaredFields();
//                for (Field declaredField : declaredFields) {
//                    System.out.println(declaredField.getName());
//                }

//                if (value != null) {
//                    System.out.println(pinCodeField.getName());
//                    map.put(columnName, value);
//                log.info("{}:{}", columnName, value);
//                }


//        Field[] personFields = MovieDto.class.getSuperclass().getDeclaredFields();
//        Field[] employeeFields = MovieDto.class.getDeclaredFields();
//        Field[] allFields = new Field[employeeFields.length + personFields.length];
//        for (Field allField : personFields) {
//            System.out.println(allField);
//        }

//        Arrays.setAll(allFields, i ->
//                (i < personFields.length ? personFields[i] : employeeFields[i - personFields.length]));

//        Movie movie = MOVIE_ROW_MAPPER.mapRow(resultSet, 0);
//&& field.getType().equals(String.class));
//


//        }

//        Set<Genre> genres = new HashSet<>();
//        List<Country> countries = new ArrayList<>();
//        List<Review> reviews = new ArrayList<>();
//        List<Movie> movies = new ArrayList<>();
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        int numRow = metaData.getColumnCount();

//
//            String columnName = metaData.getColumnName(numRow);
//        while (resultSet.next()) {
//            String value = resultSet.getString(columnName);
//            Genre genre = GENRE_ROW_MAPPER.mapRow(resultSet, i);
//            Country country = COUNTRY_ROW_MAPPER.mapRow(resultSet, i);
//            Review review = REVIEW_ROW_MAPPER.mapRow(resultSet, i);

//            log.info("{}", genre);
//            genres.add(genre);
//        }


//            Objects.requireNonNull(movie).setGenres(genres);
//            movie.setCountries(countries);
//            movie.setReviews(reviews);
//            log.info("{}", movie);
//        int i = 0;
//        while (resultSet.next()) {
//            Movie movie = movieRowMapper.mapRow(resultSet, i);
//            Set<Genre> genres = movie != null ? movie.getGenres() : null;
//            if (genres == null) {
//                genres = new HashSet<>();
//            }
//            Genre genre = genreRowMapper.mapRow(resultSet, i);
//            genres.add(genre);
//            Set<Genre> genres = movie != null ? movie.getGenres() : null;
//            movie.setGenres(genres);
//            movies.add(movie);
//        }
//        log.info("{}", movies);
//        return movies;
