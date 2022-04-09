package com.movieland.cinema.dao.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueriesContext {

    @Bean
    public String selectById() {
        return """
                SELECT mg.movie_id, m.name_translate, m.name_native, m.year_of_release, m.description,
                m.rating, m.price, m.picture_path, mc.country_id, c.country_name,
                mg.genre_id, g.name, r.review_id, r.user_id, r.text
                FROM movie m
                LEFT JOIN movie_countries AS mc ON (m.movie_id = mc.movie_id)
                LEFT JOIN country AS c ON (mc.country_id = c.country_id)
                LEFT JOIN movie_genre AS mg ON (mc.movie_id = mg.movie_id)
                LEFT JOIN genre AS g ON (mg.genre_id = g.genre_id)
                LEFT JOIN review AS r ON (mg.movie_id = r.movie_id)
                WHERE m.movie_id = ?;
                  """;
    }

    @Bean
    public String selectAllByGenre() {
        return selectMovie() + """
                FROM movie m
                LEFT JOIN movie_genre AS mg ON mg.movie_id = m.movie_id WHERE mg.genre_id=?;
                """;
    }

    @Bean
    public String selectByName() {
        return selectMovie() + """
                FROM movie m
                WHERE name_translate = :nameTranslate;
                """;
    }

    @Bean
    public String selectRandom() {
        return selectMovie() + """
                FROM movie m
                ORDER BY random() LIMIT :max;
                """;
    }

    @Bean
    public String selectAll() {
        return selectMovie() + "FROM movie m;";
    }

    private String selectMovie() {
        return "SELECT m.movie_id, m.name_native, m.name_translate, m.year_of_release, m.rating, m.price, m.description, m.picture_path ";
    }

}