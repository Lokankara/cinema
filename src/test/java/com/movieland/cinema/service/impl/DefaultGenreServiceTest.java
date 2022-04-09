package com.movieland.cinema.service.impl;

import com.movieland.cinema.dao.GenreDao;
import com.movieland.cinema.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class DefaultGenreServiceTest {
    private static final GenreDao mockDao = mock(GenreDao.class);
    private static final DefaultGenreService genreService = new DefaultGenreService(mockDao);
    private static final List<Genre> expectedGenre = new ArrayList<>();
    private static final List<Genre> actualGenre = new ArrayList<>();

    @Test
    @DisplayName("Test list with all genres Returns True")
    void getAll() {
        when(mockDao.findAll()).thenReturn(expectedGenre);
        Iterable<Genre> actual = genreService.findAll();
        actual.forEach(actualGenre::add);
        actual.forEach(genre -> assertTrue(expectedGenre.contains(genre)));
        verify(mockDao).findAll();
        checkIterable(actual);
    }

    private void checkIterable(Iterable<Genre> actual) {
        assertEquals(expectedGenre, actual);
        assertEquals(expectedGenre, actualGenre);
        assertEquals(expectedGenre.size(), actualGenre.size());
    }

    @BeforeAll
    static void init() {
        Genre moonChild = new Genre();
        moonChild.setGenreId(1L);
        moonChild.setName("comedy");
        expectedGenre.add(moonChild);

        Genre venom2 = new Genre();
        venom2.setGenreId(2L);
        venom2.setName("tragedy");
        expectedGenre.add(venom2);

        Genre doctor = new Genre();
        doctor.setGenreId(5L);
        doctor.setName("science");
        expectedGenre.add(doctor);
    }
}
