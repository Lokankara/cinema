package com.movieland.cinema.controller;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.service.pool.DefaultGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/genre")
public class GenreController {

    private final DefaultGenreService genreService;

    @GetMapping()
    public Iterable<Genre> getAll() {
        return genreService.getAll();
    }
}