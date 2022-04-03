package com.movieland.cinema.utils;

import com.movieland.cinema.domain.*;
import com.movieland.cinema.service.pool.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.movieland.cinema.utils.FileReader.readUFromUrl;
import static com.movieland.cinema.utils.Parser.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/db")
public class SetDb {
    private final DefaultMovieService movieService;
    private final DefaultPosterService posterService;
    private final DefaultGenreService genreService;
    private final DefaultReviewService reviewService;
    private final DefaultUserService userService;

    @GetMapping("/poster")
    public Iterable<Poster> addPoster() {
        String fileName = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0d/download/poster.txt";
        List<String> rows = readUFromUrl(fileName);
        List<Poster> posters = new ArrayList<>();
        Poster poster = new Poster();
        for (String row : rows) {
            String[] split = row.split(" https:");
            Movie movie = movieService.findByName(split[0]).orElseThrow();
            String link = "https:" + split[1];
            poster.setNameTranslate(split[0]);
            poster.setPicturePath(link);
            posters.add(poster);
            movie.setPicturePath(poster.getPicturePath());
            movie.setPicturePath(link);
//            Movie save = movieService.saveMovie(movie);
//            log.info("{} is Saved", movie);
//            log.info("{}", save);
        }
        return posterService.saveAll(posters);
    }

    @GetMapping("/review")
    public List<Review> addReviews() {
        List<Review> reviews = parserReview();
        reviewService.saveAll(reviews);
        return reviews;
    }

    @GetMapping("/user")
    public Iterable<User> addUsers() {
        List<User> users = parserUser();
        return userService.saveAll(users);
    }

    @GetMapping("/genre")
    public Iterable<Genre> addGenres() {
        Iterable<Genre> genres = parserGenre();
        return genreService.saveAll(genres);
    }

    @GetMapping("/genre/add")
    public void addGenre() {
//        Iterable<Genre> genres = parserGenre();
        List<Movie> movies = parserMovie();
        for (Movie movie : movies) {
            List<Genre> genres = movie.getGenres();
            for (Genre genre : genres) {
                Long genre_id = genre.getGenre_id();
                log.info("{}", genre_id);
                Optional<Genre> optionalGenre = genreService.findById(genre_id);
                log.info("{}", optionalGenre);
                Genre genreDb = optionalGenre.orElseThrow();
                Set<Movie> setMovies = genreDb.getMovies();
                boolean add = setMovies.add(movie);
                log.info("{}", add);
                Genre newGenre = genreService.add(genreDb);
                log.info("{}", newGenre);
            }
        }
//
//        genres.forEach(genre -> genre.getName());
//        });
//        return genreService.saveAll(genres);
    }
}