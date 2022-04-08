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

    private final DefaultMovieService movieService;
    private final ConvectorDto convectorDto;

    @GetMapping()
    public Iterable<MovieWithLinkDto> getAll(
            @RequestParam(defaultValue = "") String rating,
            @RequestParam(defaultValue = "") String price) {
        Params params = Params.builder().rating(rating).price(price).build();
        Iterable<Movie> movies = movieService.getAll();
        return convectorDto.movieDto(movies, params);
    }

    @GetMapping("/random")
    public Iterable<MovieWithLinkDto> getRandom(
            @RequestParam(defaultValue = "3") int size) {
        Iterable<Movie> random = movieService.getRandom(size);
        return convectorDto.movieDto(random);
    }

    @GetMapping("/genre/{genreId}")
    public Iterable<MovieWithLinkDto> getMoviesByGenreId(
            @PathVariable(value = "genreId") Long id) {
        Iterable<Movie> moviesByGenre = movieService.getByGenreId(id);
        return convectorDto.movieDto(moviesByGenre);
    }

    @GetMapping("/{movieId}")
    public Optional<Movie> getMovieById(
            @PathVariable(value = "movieId") Long id) {
        return movieService.getById(id);
    }
}
