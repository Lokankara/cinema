package com.movieland.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Params {

    private String rating;
    private String price;
    private Sort sort;

    public enum Sort {
        ASC(),
        DESC()
    }

    public boolean isAscRating() {
        return Sort.ASC.name().equals(rating);
    }

    public boolean isDescRating() {return Sort.DESC.name().equals(rating);}

    public boolean isAscPrice() {
        return Sort.ASC.name().equals(price);
    }

    public boolean isDescPrice() {
        return Sort.DESC.name().equals(price);
    }

}