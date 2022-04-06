package com.movieland.cinema.controller;

import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.domain.Params;
import com.movieland.cinema.domain.dto.ConvectorDto;
import com.movieland.cinema.domain.dto.MovieWithLinkDto;
import com.movieland.cinema.service.pool.DefaultMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie")
public class MovieController {

    private final Params params;
    private final DefaultMovieService movieService;
    private final ConvectorDto convectorDto;

    @GetMapping()
    public Iterable<MovieWithLinkDto> getAll(
            @RequestParam(defaultValue = "") String rating,
            @RequestParam(defaultValue = "") String price) {
        params.setSortByPrice(price);
        params.setSortByRating(rating);
        Iterable<Movie> all = movieService.getAll();
        return convectorDto.movieDto(all, params);
    }

    @GetMapping("/random")
    public Iterable<MovieWithLinkDto> getRandom(
            @RequestParam(defaultValue = "3") int size) {
        Iterable<Movie> random = movieService.getRandom(size);
        return convectorDto.movieDto(random, params);
    }

    @GetMapping("/genre/{genreId}")
    public Iterable<MovieWithLinkDto> getMoviesByGenreId(
            @PathVariable(value = "genreId") Long id) {
        Iterable<Movie> moviesByGenre = movieService.getByGenreId(id);
        return convectorDto.movieDto(moviesByGenre, params);
    }

    @GetMapping("/{movieId}")
    public Optional<Movie> getMovieById(
            @PathVariable(value = "movieId") Long id) {
        return movieService.getById(id);
    }
}
