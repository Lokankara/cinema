package com.movieland.cinema.domain.dto;

import com.movieland.cinema.domain.Country;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MovieWithDetailsDto {
    private Long id;
    private String nameTranslate;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    private Set<Country> countries;
    private Set<GenreWithoutMovieDto> genres;
    private List<ReviewWithoutMovieDto> reviews;
}
