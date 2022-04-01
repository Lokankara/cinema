package com.movieland.cinema.controller;

import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.domain.dto.MovieWithLink;
import com.movieland.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.movieland.cinema.utils.Parser.ParserMovie;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/add")
    public Iterable<Movie> addMovies() {
        List<Movie> movies = ParserMovie();
        Iterable<Movie> movieIterable = movieService.saveAll(movies);
        log.info("addMovies to DB {}", movieIterable);
        return movieIterable;
    }

    @GetMapping()
    public Iterable<MovieWithLink> getAll(Model model) {
        Iterable<MovieWithLink> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        log.info("Get All Movies {}", movies);
        return movies;
    }

    @GetMapping("/random")
    public Iterable<MovieWithLink> getThreeRandom(Model model) {
        Iterable<MovieWithLink> movies = movieService.getAllMovies();
//        movies.size();
        model.addAttribute("movies", movies);
        log.info("Get All Movies {}", movies);
        return movies;
    }
}

//TODO
//picturePath
//multiply genres
// [ ] [GET /v1/movie/genre/{genreId} => get movies by genre]()
// [ ] [GET /v1/movie/random => get 3 random movies]()
