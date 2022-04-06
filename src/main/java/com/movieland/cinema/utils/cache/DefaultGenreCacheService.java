package com.movieland.cinema.utils.cache;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Setter
@Service
@Component
@RequiredArgsConstructor
public class DefaultGenreCacheService implements GenreCacheService {

    private final GenreService genreService;
    private Iterable<Genre> caches;

    @Override
    public Iterable<Genre> getAll() {
        if (caches == null) {
            refresh();
        }
        return caches;
    }

    private void refresh() {
        caches = genreService.getAll();
    }

    @Override
    public Optional<Genre> findById(Long genre_id) {
        return genreService.findById(genre_id);
    }

    @Override
    public void clearCache() {
        caches = new ArrayList<>();
    }

    @Override
    @Scheduled(cron = "0 0 0/3 * * ?")
    public void scheduler() {
        refresh();
    }
}