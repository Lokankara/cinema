package com.movieland.cinema.service.pool;

import com.movieland.cinema.domain.Poster;
import com.movieland.cinema.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultPosterService {

    private final PosterRepository posterRepository;

    public Poster save(Poster poster) {
        return posterRepository.save(poster);
    }

    public Iterable<Poster> saveAll(List<Poster> posters) {
        return posterRepository.saveAll(posters);
    }

    public Iterable<Poster> getAll() {
        return posterRepository.findAll();
    }

}
