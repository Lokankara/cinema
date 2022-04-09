package com.movieland.cinema.dao.jdbc.mapper.impl;

import com.movieland.cinema.entity.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Review review = new Review();

        long id = resultSet.getLong("review_id");
        String text = resultSet.getString("text");

        review.setReviewId(id);
        review.setText(text);

        return review;
    }
}
