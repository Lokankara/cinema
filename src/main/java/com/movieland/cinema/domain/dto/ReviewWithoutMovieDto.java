package com.movieland.cinema.domain.dto;

import lombok.Data;

@Data
public class ReviewWithoutMovieDto {
    private Long id;
    private String nickname;
    private String text;
}
