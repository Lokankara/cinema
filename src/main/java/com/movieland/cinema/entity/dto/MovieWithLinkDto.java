package com.movieland.cinema.entity.dto;

import lombok.Data;

@Data
public class MovieWithLinkDto {
    private Long movieId;
    private String nameTranslate;
    private String nameNative;
    private String yearOfRelease;
    private String picturePath;
    private double rating;
    private double price;
}