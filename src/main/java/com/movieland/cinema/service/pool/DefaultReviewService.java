package com.movieland.cinema.service.pool;

import com.movieland.cinema.domain.Review;
import com.movieland.cinema.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultReviewService {

    private final ReviewRepository reviewRepository;

    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

}
