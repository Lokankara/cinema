package com.movieland.cinema.service;

import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.domain.dto.MovieWithLink;
import com.movieland.cinema.repository.MovieRepository;
import com.movieland.cinema.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.movieland.cinema.utils.dtoConvector.MovieDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie add(Movie movie) {
        return movieRepository.save(movie);
    }

    public Iterable<MovieWithLink> getAllMovies() {
        Iterable<Movie> movies = movieRepository.findAll();
        Iterable<MovieWithLink> movieWithLinks = MovieDto(movies);
        return movieWithLinks;
    }

    public Iterable<Movie> saveAll(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }
}
