package com.movieland.cinema.utils;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.domain.dto.GenreWithoutMovie;
import com.movieland.cinema.domain.dto.MovieWithLink;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class dtoConvector {

    public static Iterable<MovieWithLink> MovieDto(Iterable<Movie> movies) {
        List<MovieWithLink> movieWithLinks = new ArrayList<>();
        movies.forEach(movie -> {
            copyMovieProps(movieWithLinks, movie);
        });
        return movieWithLinks;
    }

    public static Iterable<GenreWithoutMovie> GenreDto(Iterable<Genre> genres) {
        List<GenreWithoutMovie> genreWithoutMovies = new ArrayList<>();
        genres.forEach(genre ->
                copyGenreProps(genreWithoutMovies, genre));
        return genreWithoutMovies;
    }

    private static List<GenreWithoutMovie> copyGenreProps(List<GenreWithoutMovie> genreWithoutMovies, Genre genre) {
        GenreWithoutMovie genreWithoutMovie = new GenreWithoutMovie();
        BeanUtils.copyProperties(genre, genreWithoutMovie, "movie");
        genreWithoutMovies.add(genreWithoutMovie);
        return genreWithoutMovies;
    }

    public static List<MovieWithLink> copyMovieProps(List<MovieWithLink> movieWithLinks, Movie movie) {
        MovieWithLink movieWithLink = new MovieWithLink();
        BeanUtils.copyProperties(movie, movieWithLink, "movie");
        movieWithLinks.add(movieWithLink);
        return movieWithLinks;
    }

}
