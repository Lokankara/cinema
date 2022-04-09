package com.movieland.cinema.controller;

import com.movieland.cinema.entity.Genre;
import com.movieland.cinema.entity.dto.GenreDto;
import com.movieland.cinema.entity.dto.MovieDtoConvector;
import com.movieland.cinema.service.impl.DefaultGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/genre")
public class GenreController {

    private final DefaultGenreService genreService;
    private final MovieDtoConvector movieDtoConvector;

    @GetMapping()
    public Iterable<GenreDto> getAll() {
        Iterable<Genre> genres = genreService.findAll();
        return movieDtoConvector.genreDto(genres);
    }
}