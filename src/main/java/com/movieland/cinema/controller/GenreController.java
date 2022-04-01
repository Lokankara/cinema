package com.movieland.cinema.controller;

import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.domain.dto.GenreWithoutMovie;
import com.movieland.cinema.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.movieland.cinema.utils.Parser.ParserGenre;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre/add")
    public Iterable<Genre> addGenres() {
        Iterable<Genre> genres = ParserGenre();
        return genreService.saveAll(genres);
    }

    @GetMapping("/genre")
    public Iterable<GenreWithoutMovie> getAll(Model model) {
        Iterable<GenreWithoutMovie> genres = genreService.getAll();
        model.addAttribute("genre", genres);
        return genres;
    }

    @GetMapping("/random/genre/{genreId}")
    public Genre getById(
            @PathVariable(value = "genreId") Long id) {
        return genreService.getById(id);
    }
}