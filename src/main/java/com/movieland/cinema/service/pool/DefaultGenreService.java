package com.movieland.cinema.service.pool;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.repository.GenreRepository;
import com.movieland.cinema.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {

    private final GenreRepository genreRepository;

    public Genre add(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Iterable<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Long genre_id) {
        return Optional.empty();
    }

    public Iterable<Genre> saveAll(Iterable<Genre> genres) {
        return genreRepository.saveAll(genres);
    }
}