package com.movieland.cinema.service.pool;

import com.movieland.cinema.dao.MovieDao;
import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {
    final private MovieDao movieDao;

    @Override
    public Iterable<Movie> getAll() {
        return movieDao.findAll();
    }

    @Override
    public Iterable<Movie> getRandom(int max) {
        return movieDao.getRandomInt(max);
    }

    @Override
    public Optional<Movie> getByName(String name) {
        return movieDao.findByName(name);
    }

    @Override
    public Iterable<Movie> getByGenreId(Long id) {
        return movieDao.findMoviesByGenre(id);
    }

    @Override
    public Optional<Movie> getById(Long id) {
        return movieDao.findById(id);
    }
}
