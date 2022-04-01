package com.movieland.cinema.domain.dto;

import com.movieland.cinema.domain.Country;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MovieWithDetails {
    private Long id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    private Set<Country> countries;
    private Set<GenreWithoutMovie> genres;
    private List<ReviewWithoutMovie> reviews;
}
