package com.movieland.cinema.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private String id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    // @ManyToMany(fetch = FetchType.LAZY,
    //         cascade = {
    //                 CascadeType.PERSIST,
    //                 CascadeType.MERGE
    //         })
    // @JoinTable(name = "movie_roles",
    //         joinColumns = {@JoinColumn(name = "movie_id")},
    //         inverseJoinColumns = {@JoinColumn(name = "role_id")})
    // private List<Genre> roles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}