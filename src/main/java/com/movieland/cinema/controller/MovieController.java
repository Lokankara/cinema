package com.movieland.cinema.controller;

import com.movieland.cinema.entity.Movie;
import com.movieland.cinema.entity.Params;
import com.movieland.cinema.entity.dto.MovieDto;
import com.movieland.cinema.entity.dto.MovieDtoConvector;
import com.movieland.cinema.entity.dto.MovieWithLinkDto;
import com.movieland.cinema.service.impl.DefaultMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie")
public class MovieController {

    private final DefaultMovieService movieService;
    private final MovieDtoConvector movieDtoConvector;

    @GetMapping()
    public Iterable<MovieWithLinkDto> getAll(
            @RequestParam(defaultValue = "") String rating,
            @RequestParam(defaultValue = "") String price) {
        Params params = Params.builder().rating(rating).price(price).build();
        Iterable<Movie> movies = movieService.getAll();
        return movieDtoConvector.mapMoviesToMoviesWithLinkDto(movies, params);
    }
//TODO random sql
    @GetMapping("/random")
    public Iterable<MovieWithLinkDto> getRandom() {
        Iterable<Movie> random = movieService.getRandom();
        return movieDtoConvector.mapMoviesToMoviesWithLinkDto(random);
    }

    @GetMapping("/genre/{genreId}")
    public Iterable<MovieWithLinkDto> getMoviesByGenreId(
            @PathVariable(value = "genreId") Long id) {
        Iterable<Movie> moviesByGenre = movieService.getByGenreId(id);
        return movieDtoConvector.mapMoviesToMoviesWithLinkDto(moviesByGenre);
    }

    @GetMapping("/{movieId}")
    public Optional<Movie> getMovieById(
            @PathVariable(value = "movieId") Long id) {
        return movieService.getById(id);
    }
}
