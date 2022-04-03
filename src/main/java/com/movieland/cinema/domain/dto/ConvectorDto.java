package com.movieland.cinema.domain.dto;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@ComponentScan("com.movieland.cinema.dao.jdbc")
public class ConvectorDto {

    public List<MovieWithLinkDto> movieDto(Iterable<Movie> movies) {
        List<MovieWithLinkDto> moviesWithLinkDto = new ArrayList<>();
        movies.forEach(movie ->
                copyMovieProps(moviesWithLinkDto, movie));
        return moviesWithLinkDto;
    }

    public List<GenreWithoutMovieDto> genreDto(Iterable<Genre> genres) {
        List<GenreWithoutMovieDto> genreWithoutMovies = new ArrayList<>();
        genres.forEach(genre ->
                copyGenreProps(genreWithoutMovies, genre));
        return genreWithoutMovies;
    }

    public Iterable<GenreWithoutMovieDto> copyGenreProps(
            List<GenreWithoutMovieDto> genreWithoutMovies, Genre genre) {
        GenreWithoutMovieDto genreWithoutMovie = new GenreWithoutMovieDto();
        BeanUtils.copyProperties(genre, genreWithoutMovie);
        genreWithoutMovies.add(genreWithoutMovie);
        return genreWithoutMovies;
    }

    public Iterable<MovieWithLinkDto> copyMovieProps(
            List<MovieWithLinkDto> movieWithLinks, Movie movie) {
        MovieWithLinkDto movieWithLink = new MovieWithLinkDto();
        BeanUtils.copyProperties(movie, movieWithLink);
        movieWithLinks.add(movieWithLink);
        return movieWithLinks;
    }
}
