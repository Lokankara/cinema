package com.movieland.cinema.repository;

import com.movieland.cinema.domain.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

//    @Query("select p from Poster p left join p movie where movie.nameRussian=p.nameRussian")
//    Set<Movie> findMoviesByPosters();
}
