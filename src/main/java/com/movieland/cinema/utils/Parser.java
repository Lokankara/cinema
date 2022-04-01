package com.movieland.cinema.utils;

import com.movieland.cinema.domain.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.movieland.cinema.utils.FileReader.readUFromUrl;

@Slf4j
public class Parser {

    public static List<Review> ParserReview() {
        String fileName = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0f/download/review.txt";
        List<String> rows = readUFromUrl(fileName);
        List<Review> reviews = new ArrayList<>();
        Review review = new Review();

        for (int i = 0; i < rows.size(); i++) {
            if (i % 3 == 0) {
                review = new Review();
//                review.setMovieName(rows.get(i));
//            } else if (i % 3 == 1) {
//                review.setUserName(rows.get(i));
//            } else if (i % 3 == 2) {
//                review.setReview(rows.get(i));
                reviews.add(review);
            }
        }
        return reviews;
    }

    public static List<User> ParserUser() {
        String fileName = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e11/download/user.txt";
        List<String> rows = readUFromUrl(fileName);
        List<User> users = new ArrayList<>();
        User user = new User();

        for (int i = 0; i < rows.size(); i++) {
            if (i % 3 == 0) {
                user = new User();
//                user.setUserName(rows.get(i));
            } else if (i % 3 == 1) {
                user.setEmail(rows.get(i));
            } else if (i % 3 == 2) {
                user.setPassword(rows.get(i));
                users.add(user);
            }
        }
        return users;
    }

    public static List<Poster> ParserPoster() {
        String fileName = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0d/download/poster.txt";
        List<String> rows = readUFromUrl(fileName);
        List<Poster> posters = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            String[] split = rows.get(i).split(" https:");
            Poster poster = new Poster();
            poster.setNameRussian(split[0]);
            poster.setPicturePath("https:" + split[1]);
            posters.add(poster);
        }
        return posters;
    }

    public static List<Movie> ParserMovie() {
        String fileName = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0b/download/movie.txt";
        List<String> rows = readUFromUrl(fileName);
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();

        for (int i = 0; i < rows.size(); i++) {
            if (i % 7 == 0) {
                movie = new Movie();
                String[] split = rows.get(i).split("/");
                movie.setNameRussian(split[0]);
                movie.setNameNative(split[1]);

            } else if (i % 7 == 1) {
                movie.setYearOfRelease(rows.get(i));

            } else if (i % 7 == 2) {
                Country country = new Country();
                String s = rows.get(i);
                List<Country> countries = new ArrayList<>();
                country.setName(s);
                countries.add(country);

            } else if (i % 7 == 3) {
                String[] split = rows.get(i).split(", ");
                List<Genre> set = new ArrayList<>();
                for (String s : split) {
                    Genre genre = new Genre();
                    genre.setName(s);
                    set.add(genre);
                }
                movie.setGenres(set);

            } else if (i % 7 == 4) {
                movie.setDescription(rows.get(i));

            } else if (i % 7 == 5) {
                String row = rows.get(i).split(":")[1];
                movie.setRating(Double.parseDouble(row));

            } else if (i % 7 == 6) {
                String row = rows.get(i).split(":")[1];
                movie.setPrice(Double.parseDouble(row));
                movies.add(movie);
            }
        }
        return movies;
    }

    public static Iterable<Genre> ParserGenre() {
        String fileName = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e09/download/genre.txt";
        List<String> rows = readUFromUrl(fileName);
        List<Genre> genres = new ArrayList<>();

        for (String row : rows) {
            Genre genre = new Genre();
            genre.setName(row);
            genres.add(genre);
        }
        return genres;
    }
}
