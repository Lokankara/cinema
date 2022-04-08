package com.movieland.cinema.service.pool;

import com.movieland.cinema.dao.GenreDao;
import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }
}