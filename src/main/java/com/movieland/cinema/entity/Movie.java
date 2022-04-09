package com.movieland.cinema.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long movieId;
    private String nameTranslate;
    private String nameNative;
    private String yearOfRelease;
    @Column(name = "description", length = 1024)
    private String description;
    private double rating;
    private double price;
    private String picturePath;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "movie_countries",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "country_id")})
    private Set<Country> countries;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "movie_genre",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return movieId != null && Objects.equals(movieId, movie.movieId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "movieId = " + movieId + ", " +
                "nameNative = " + nameNative + ", " +
                "nameTranslate = " + nameTranslate + ", " +
                "yearOfRelease = " + yearOfRelease + ", " +
                "rating = " + rating + ", " +
                "price = " + price + ", " +
                "description = " + description + ", " +
                "picturePath = " + picturePath + ")";
    }
}
