package com.movieland.cinema.domain.dto;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.domain.Params;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    public Iterable<MovieWithLinkDto> movieDto(Iterable<Movie> movies, Params params) {
        List<MovieWithLinkDto> moviesWithLinkDto = movieDto(movies);
        if (params.isDescPrice() || params.isAscPrice()) {
            sortedByPrice(params, moviesWithLinkDto);
        }

        if (params.isDescRating() || params.isAscRating()) {
            sortedByRating(params, moviesWithLinkDto);
        }
        return moviesWithLinkDto;
    }

    public Iterable<GenreWithoutMovieDto> genreDto(Iterable<Genre> genres) {
        List<GenreWithoutMovieDto> genreWithoutMovies = new ArrayList<>();
        genres.forEach(genre ->
                copyGenreProps(genreWithoutMovies, genre));
        return genreWithoutMovies;
    }

    public void copyGenreProps(
            List<GenreWithoutMovieDto> genreWithoutMovies, Genre genre) {
        GenreWithoutMovieDto genreWithoutMovie = new GenreWithoutMovieDto();
        BeanUtils.copyProperties(genre, genreWithoutMovie);
        genreWithoutMovies.add(genreWithoutMovie);
    }

    public void copyMovieProps(
            List<MovieWithLinkDto> movieWithLinks, Movie movie) {
        MovieWithLinkDto movieWithLink = new MovieWithLinkDto();
        BeanUtils.copyProperties(movie, movieWithLink);
        movieWithLinks.add(movieWithLink);
    }

    private void sortedByRating(Params params,
                                List<MovieWithLinkDto> moviesWithLinkDto) {

        moviesWithLinkDto.sort(Comparator.comparingDouble(MovieWithLinkDto::getRating));

        if (params.isDescRating()) {
            reverseList(moviesWithLinkDto);
        }

        log.info("Sorted by Rating {}", params.getRating());
    }

    private void sortedByPrice(Params params,
                               List<MovieWithLinkDto> moviesWithLinkDto) {

        moviesWithLinkDto.sort(Comparator.comparingDouble(MovieWithLinkDto::getPrice));
        if (params.isDescPrice()) {
            reverseList(moviesWithLinkDto);
        }
        log.info("Sorted by Price {}", params.getPrice());
    }

    private <T> void reverseList(List<T> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        T value = list.remove(0);
        reverseList(list);
        list.add(value);
    }
}
