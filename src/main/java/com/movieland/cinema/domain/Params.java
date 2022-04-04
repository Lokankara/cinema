package com.movieland.cinema.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Service
@RequiredArgsConstructor
public class Params {
    String sortByRating;
    String sortByPrice;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Language language;

}
