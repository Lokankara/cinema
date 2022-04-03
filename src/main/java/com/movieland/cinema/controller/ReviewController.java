package com.movieland.cinema.controller;

import com.movieland.cinema.domain.Review;
import com.movieland.cinema.service.pool.DefaultReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/review")
public class ReviewController {

    private final DefaultReviewService reviewService;

    @GetMapping()
    public Iterable<Review> getAll() {
        return reviewService.getAllReviews();
    }
}
