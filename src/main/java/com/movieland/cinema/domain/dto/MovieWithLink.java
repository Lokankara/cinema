package com.movieland.cinema.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieWithLink {
    private Long movie_id;
    private String nameNative;
    private String nameRussian;
    private String yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;
}