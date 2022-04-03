package com.movieland.cinema.controller;

import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.domain.dto.MovieWithLinkDto;
import com.movieland.cinema.service.pool.DefaultMovieService;
import com.movieland.cinema.domain.dto.ConvectorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie")
public class MovieController {

    private final DefaultMovieService movieService;
    private final ConvectorDto convectorDto;

    @GetMapping()
    public List<MovieWithLinkDto> getAll() {
        Iterable<Movie> all = movieService.getAll();
        return convectorDto.movieDto(all);
    }

    @GetMapping("/random")
    public Iterable<MovieWithLinkDto> getRandom() {
        int max = 3;
        Iterable<Movie> random = movieService.getRandom(max);
        log.info("Get {} random movies from DataBase", max);
        return convectorDto.movieDto(random);
    }

    @GetMapping("/genre/{genreId}")
    public Iterable<Movie> getMovieByGenreId(
            @PathVariable(value = "genreId") Long id) {
        return movieService.getByGenreId(id);
    }
}
