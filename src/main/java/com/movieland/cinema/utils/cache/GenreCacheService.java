package com.movieland.cinema.utils.cache;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.service.GenreService;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Optional;

public interface GenreCacheService extends GenreService {

    Iterable<Genre> getAll();

    Optional<Genre> findById(Long genre_id);

    void clearCache();

    @Scheduled(cron = "0 0 0/3 * * ?")
    void scheduler();

    void setCaches(Iterable<Genre> caches);
}
