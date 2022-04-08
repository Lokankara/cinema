package com.movieland.cinema.utils.cache;

import com.movieland.cinema.dao.GenreDao;
import com.movieland.cinema.domain.Genre;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Service
@Primary
@Component
@Repository
@RequiredArgsConstructor
public class DefaultGenreCacheService implements GenreCacheDao {

    private final GenreDao genreDao;
    private volatile List<Genre> caches;

    public void refresh() {
        caches = genreDao.findAll();
    }

    public void clearCache() {
        caches = new ArrayList<>();
    }

    @PostConstruct
    @Scheduled(cron = "${cron.expression}")
    public void scheduler() {
        clearCache();
        refresh();
    }

    @Override
    public List<Genre> findAll() {
        if (caches == null) {
            refresh();
        }
        return new ArrayList<>(caches);
    }
}