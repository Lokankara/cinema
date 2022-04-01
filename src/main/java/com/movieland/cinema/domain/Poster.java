package com.movieland.cinema.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "poster")
public class Poster {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long poster_id;
    private String picturePath;
    private String nameRussian;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Poster poster = (Poster) o;
        return poster_id != null && Objects.equals(poster_id, poster.poster_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Poster{" +
                "poster_id=" + poster_id +
                ", picturePath='" + picturePath + '\'' +
                ", nameRussian='" + nameRussian + '\'' +
                '}';
    }

}
