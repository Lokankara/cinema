package com.movieland.cinema.controller;

import com.movieland.cinema.domain.Poster;
import com.movieland.cinema.service.pool.DefaultPosterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/poster")
public class PosterController {

    private final DefaultPosterService posterService;

    @GetMapping()
    public Iterable<Poster> getAll() {
        Iterable<Poster> posters = posterService.getAll();
        log.info("Find all <Posters> from db: {}", posters);
        return posters;
    }
}
