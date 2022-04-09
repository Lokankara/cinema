package com.movieland.cinema.entity.dto;

import com.movieland.cinema.entity.Genre;
import com.movieland.cinema.entity.Movie;
import com.movieland.cinema.entity.Params;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Configuration
public class MovieDtoConvector {

    public List<MovieWithLinkDto> mapMoviesToMoviesWithLinkDto(Iterable<Movie> movies) {
        List<MovieWithLinkDto> moviesWithLinkDto = new ArrayList<>();
        movies.forEach(movie ->
                copyMovieProps(moviesWithLinkDto, movie));
        return moviesWithLinkDto;
    }

    public Iterable<MovieWithLinkDto> mapMoviesToMoviesWithLinkDto(Iterable<Movie> movies, Params params) {
        List<MovieWithLinkDto> moviesWithLinkDto = mapMoviesToMoviesWithLinkDto(movies);
        if (params.isDescPrice() || params.isAscPrice()) {
            sortedByPrice(params, moviesWithLinkDto);
        }

        if (params.isDescRating() || params.isAscRating()) {
            sortedByRating(params, moviesWithLinkDto);
        }
        return moviesWithLinkDto;
    }

    public Iterable<GenreDto> genreDto(Iterable<Genre> genres) {
        List<GenreDto> genreWithoutMovies = new ArrayList<>();
        genres.forEach(genre ->
                copyGenreProps(genreWithoutMovies, genre));
        return genreWithoutMovies;
    }

    public void copyGenreProps(
            List<GenreDto> genreWithoutMovies, Genre genre) {
        GenreDto genreWithoutMovie = new GenreDto();
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
