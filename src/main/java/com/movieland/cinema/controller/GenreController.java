package com.movieland.cinema.controller;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.dto.ConvectorDto;
import com.movieland.cinema.domain.dto.GenreWithoutMovieDto;
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
    private final ConvectorDto convectorDto;

    @GetMapping()
    public Iterable<GenreWithoutMovieDto> getAll() {
        Iterable<Genre> genres = genreService.getAll();
        return convectorDto.genreDto(genres);
    }
}