package com.movieland.cinema.service.pool;

import com.movieland.cinema.CinemaApplication;
import com.movieland.cinema.dao.MovieDao;
import com.movieland.cinema.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CinemaApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor
public class DefaultMovieServiceTest {
    private static final MovieDao mockDao = mock(MovieDao.class);
    private static final DefaultMovieService movieService = new DefaultMovieService(mockDao);
    private static final List<Movie> expectedMovie = new ArrayList<>();
    private static final List<Movie> actualMovie = new ArrayList<>();


    @Test
    @DisplayName("Test Iterable with all movies, check if List contains movies Returns True")
    void getAll() {
        when(mockDao.findAll()).thenReturn(expectedMovie);
        Iterable<Movie> actual = movieService.getAll();
        actual.forEach(actualMovie::add);
        actual.forEach(movie -> assertTrue(expectedMovie.contains(movie)));
        verify(mockDao).findAll();
        checkIterable(actual);
    }

    @Test
    @DisplayName("Test random list with max size movies Returns True")
    void getRandom() {
        for (int i = 0; i < 2; i++) {
            when(mockDao.getRandomInt(i)).thenReturn(expectedMovie);
            Iterable<Movie> actual = movieService.getRandom(i);
            actual.forEach(movie -> assertTrue(expectedMovie.contains(movie)));
            verify(mockDao).getRandomInt(i);
            checkIterable(actual);
        }
    }

    @Test
    @DisplayName("Test find By Name Translate Returns Optional Movie")
    void findByNameTranslate() {
        Optional<Movie> expectedMovie = Optional.of(createNewMovie());
        when(mockDao.findByName("Веном 2")).thenReturn(expectedMovie);
        Optional<Movie> optionalMovie = movieService.getByName("Веном 2");
        verify(mockDao).findByName("Веном 2");
        assertEquals(expectedMovie, optionalMovie);
    }

    private void checkIterable(Iterable<Movie> actual) {
        assertEquals(expectedMovie, actual);
        assertEquals(expectedMovie, actualMovie);
        assertEquals(expectedMovie.size(), actualMovie.size());
    }

    @BeforeAll
    static void init() {
        fillTest();
    }

    public static void fillTest() {

        Movie moonKnight = new Movie();
        moonKnight.setMovieId(1L);
        moonKnight.setNameTranslate("Лунный рыцарь");
        moonKnight.setDescription("new Moon Knight");
        moonKnight.setNameNative("Moon Knight");
        moonKnight.setYearOfRelease("2022");
        moonKnight.setRating(9.1);
        moonKnight.setPrice(99.9);
        moonKnight.setPicturePath("https://static.hdrezka.ac/i/2022/3/11/la1c4f936b11ctn49p58t.jpg");
        expectedMovie.add(moonKnight);


        Movie venom2 = new Movie();
        venom2.setMovieId(2L);
        venom2.setNameTranslate("Веном 2");
        venom2.setNameNative("Venom: Let There Be Carnage");
        venom2.setDescription("new Venom: Let There Be Carnage");
        venom2.setYearOfRelease("2021");
        venom2.setRating(6.9);
        venom2.setPrice(99.99);
        venom2.setPicturePath("https://static.hdrezka.ac/i/2021/11/29/me526bdb7ea5dad36a32g.jpg");
        expectedMovie.add(venom2);
        createNewMovie();
        when(mockDao.findAll()).thenReturn(expectedMovie);
    }

    private static Movie createNewMovie() {
        Movie doctor = new Movie();
        doctor.setMovieId(5L);
        doctor.setNameTranslate("Доктор Стрэндж");
        doctor.setNameNative("Doctor Strange");
        doctor.setYearOfRelease("2022");
        doctor.setDescription("new Doctor Strange");
        doctor.setRating(9.5);
        doctor.setPrice(100.1);
        doctor.setPicturePath("https://static.hdrezka.ac/i/2022/2/18/j09948c99c8cfwk71c80m.png");
        expectedMovie.add(doctor);
        return doctor;
    }
}