package com.movieland.cinema.repository;

import com.movieland.cinema.entity.Country;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier(value = "userRepository")
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCountryName(String name);
}