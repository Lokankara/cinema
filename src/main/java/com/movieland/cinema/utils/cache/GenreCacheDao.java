package com.movieland.cinema.utils.cache;

import com.movieland.cinema.dao.GenreDao;
import org.springframework.scheduling.annotation.Scheduled;

public interface GenreCacheDao extends GenreDao {

    void clearCache();

    void refresh();

    @Scheduled()
    void scheduler();
}