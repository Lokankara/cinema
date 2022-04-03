package com.movieland.cinema.service.pool;

import com.movieland.cinema.domain.Review;
import com.movieland.cinema.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Iterable<Review> saveAll(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }
}