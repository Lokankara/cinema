package com.movieland.cinema.service.pool;

import com.movieland.cinema.CinemaApplication;
import com.movieland.cinema.domain.Genre;
import com.movieland.cinema.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = CinemaApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor
class GenreServiceTest {

    private static final GenreRepository mock = mock(GenreRepository.class);
    private static final DefaultGenreService genreService = new DefaultGenreService(mock);
    private static final List<Genre> expectedGenre = new ArrayList<>();

    @Test
    @DisplayName(value = "Test get all genres return true")
    public void getAll() {
        GenreRepository mock = mock(GenreRepository.class);
        when(mock.findAll()).thenReturn(expectedGenre);
        Iterable<Genre> actualGenre = genreService.getAll();
        actualGenre.forEach(genre -> assertTrue(expectedGenre.contains(genre)));
    }

    @BeforeAll
    static void init() {
        Genre drama = new Genre();
        drama.setGenreId(1L);
        drama.setName("драма");
        expectedGenre.add(drama);

        Genre criminal = new Genre();
        criminal.setGenreId(2L);
        criminal.setName("криминал");
        expectedGenre.add(criminal);

    }
}