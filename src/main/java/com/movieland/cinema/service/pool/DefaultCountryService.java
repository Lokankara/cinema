package com.movieland.cinema.service.pool;

import com.movieland.cinema.domain.Country;
import com.movieland.cinema.repository.CountryRepository;
import com.movieland.cinema.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultCountryService implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Optional<Country> getByName(String username) {
        return countryRepository.findByCountryName(username);
    }
}
