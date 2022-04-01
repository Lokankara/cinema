package com.movieland.cinema.domain.dto;

import lombok.Data;

@Data
public class ReviewWithoutMovie {
    private Long id;
    private String nickname;
    private String text;
}
