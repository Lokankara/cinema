package com.movieland.cinema.service;

import com.movieland.cinema.dao.GenreDao;
import org.springframework.scheduling.annotation.Scheduled;

public interface GenreCacheService extends GenreDao {

    void refresh();

    @Scheduled()
    void scheduler();
}