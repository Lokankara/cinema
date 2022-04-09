package com.movieland.cinema.service.impl;

import com.movieland.cinema.entity.Review;
import com.movieland.cinema.repository.ReviewRepository;
import com.movieland.cinema.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultReviewService implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
