package com.movieland.cinema.entity.dto;

import com.movieland.cinema.entity.Country;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MovieDto {
    private String movieId;
    private String nameTranslate;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    private Set<Country> countries;
    private Set<GenreDto> genres;
    private List<ReviewDto> reviews;
}
