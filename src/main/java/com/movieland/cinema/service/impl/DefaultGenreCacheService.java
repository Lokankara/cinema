package com.movieland.cinema.service.impl;

import com.movieland.cinema.dao.GenreDao;
import com.movieland.cinema.entity.Genre;
import com.movieland.cinema.service.GenreCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Primary
@Repository
@RequiredArgsConstructor
public class DefaultGenreCacheService implements GenreCacheService {

    private final GenreDao genreDao;
    private volatile List<Genre> caches;

    public void refresh() {
        caches = genreDao.findAll();
    }

    @PostConstruct
    @Scheduled(cron = "${cron.expression}")
    public void scheduler() {
        refresh();
    }

    @Override
    public List<Genre> findAll() {
        return new ArrayList<>(caches);
    }
}