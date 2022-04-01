package com.movieland.cinema.service;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.dto.GenreWithoutMovie;
import com.movieland.cinema.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.movieland.cinema.utils.dtoConvector.GenreDto;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre add(Genre genre) {
        return genreRepository.save(genre);
    }

    public Iterable<GenreWithoutMovie> getAll() {
        Iterable<Genre> genres = genreRepository.findAll();
        Iterable<GenreWithoutMovie> genreWithoutMovies = GenreDto(genres);
        return genreWithoutMovies;
    }

    public Iterable<Genre> saveAll(Iterable<Genre> genres) {
        return genreRepository.saveAll(genres);
    }

    public Genre getById(Long id) {
        return genreRepository.findById(id).orElseThrow();
    }
}