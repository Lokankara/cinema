package com.movieland.cinema.service.impl;

import com.movieland.cinema.CinemaApplication;
import com.movieland.cinema.dao.GenreDao;
import com.movieland.cinema.entity.Genre;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CinemaApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor
class GenreServiceTest {

    private static final GenreDao mock = mock(GenreDao.class);
//    private static final DefaultGenreCacheService genreService = new DefaultGenreCacheService(mock);
    private static final List<Genre> expectedGenre = new ArrayList<>();
    private static final DefaultGenreService genreService = new DefaultGenreService(mock);

    @Test
    @DisplayName(value = "Test get all genres and contains return true ")
    public void getAll() {
        GenreRepository mock = mock(GenreRepository.class);
        when(mock.findAll()).thenReturn(expectedGenre);
        Iterable<Genre> actualGenre = genreService.findAll();
        actualGenre.forEach(genre -> assertTrue(expectedGenre.contains(genre)));
//        assertEquals(expectedGenre, actualGenre);
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