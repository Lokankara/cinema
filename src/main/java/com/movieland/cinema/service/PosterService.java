package com.movieland.cinema.service;

import com.movieland.cinema.domain.Poster;
import com.movieland.cinema.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PosterService {

    private final PosterRepository posterRepository;

    public Poster addPoster(Poster poster) {
        return posterRepository.save(poster);
    }

    public Iterable<Poster> saveAll(List<Poster> posters) {
        return posterRepository.saveAll(posters);
    }

    public Iterable<Poster> getAll() {
        return posterRepository.findAll();
    }

}
