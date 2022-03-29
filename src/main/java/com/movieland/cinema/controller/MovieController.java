package com.movieland.cinema.controller;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.movieland.cinema.utils.FileReader.readUFromUrl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/movies")
public class MovieController {

    private final MovieService movieService;
    String fileName = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0b/download/movie.txt";

    @GetMapping("/add")
    public String addMovie() {

        List<String> rows = readUFromUrl(fileName);
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();

        for (int i = 0; i < rows.size(); i++) {
            if (i % 7 == 0) {
                movie = new Movie();
                movie.setMovieName(rows.get(i));
            } else if (i % 7 == 1) {
                movie.setYear(Integer.parseInt(rows.get(i)));
            } else if (i % 7 == 2) {
                movie.setCounty(rows.get(i));
            } else if (i % 7 == 3) {
                String[] split = rows.get(i).split(" ,");
                Set<Genre> set = new HashSet<>();
                Genre genre = new Genre();
                for (String s : split) {
                    genre.setGenre(s);
                    set.add(genre);
                }
                movie.setGenres(set);
            } else if (i % 7 == 4) {
                movie.setDescription(rows.get(i));
            } else if (i % 7 == 5) {
                String row = rows.get(i).split(":")[1];
                movie.setRating(Double.parseDouble(row));
            } else if (i % 7 == 6) {
                String row = rows.get(i).split(":")[1];
                movie.setPrice(Double.parseDouble(row));
                movies.add(movie);
                log.info("movie: {}", movie);
                movieService.addMovie(movie);
            }
        }
        return "redirect:/users";
    }

    @GetMapping()
    public Iterable<Movie> getAll(Model model) {

        Iterable<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return movies;
    }
}