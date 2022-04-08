package com.movieland.cinema.dao.jdbc;

import com.movieland.cinema.CinemaApplication;
import com.movieland.cinema.dao.MovieDao;
import com.movieland.cinema.domain.Movie;
import com.movieland.cinema.service.pool.DefaultMovieService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static com.movieland.cinema.service.pool.DefaultMovieServiceTest.fillTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CinemaApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor
class JdbcMovieDaoTest {
    private static final List<Movie> expectedMovie = new ArrayList<>();
    private static final MovieDao mockDao = mock(MovieDao.class);
    private static final List<Movie> actualMovie = new ArrayList<>();
    private static DefaultMovieService movieService;

    @BeforeAll
    static void init() {
        fillTest();
    }

    @BeforeAll
    static void beforeAll() {
        movieService = new DefaultMovieService(mockDao);
    }

    @Test
    @DisplayName("Test Find All method")
    void testFindAllInvokes() {
        movieService.getAll();
        verify(mockDao, times(2)).findAll();
    }

    @Test
    @DisplayName("Test Find All return Not Null")
    void testFindAllIsNotNull() {
        Iterable<Movie> movies = movieService.getAll();
        movies.forEach(movie -> {
            System.out.println(movie);
            assertNotEquals(0, movie.getMovieId());
            assertNotNull(movie.getNameTranslate());
            assertNotNull(movie.getNameNative());
            assertNotNull(movie.getPrice());
            assertNotNull(movie.getDescription());
            assertNotNull(movie.getYearOfRelease());
            assertNotNull(movie.getRating());
            assertNotNull(movie.getPrice());
            assertNotNull(movie.getPicturePath());
        });
    }

    @Test
    @DisplayName("Test Fetch All Movies successful")
    public void whenInvokedFindAllThenFetchAllMovies() {

        when(mockDao.findAll()).thenReturn(expectedMovie);
        Iterable<Movie> actualMovie = movieService.getAll();

        int c = 0;
        for (Movie movie : actualMovie) {
            assertEquals(movie, expectedMovie.get(c));
            c++;
        }
        verify(mockDao).findAll();
        checkIterable(actualMovie);
    }

    private void checkIterable(Iterable<Movie> actual) {
        assertEquals(expectedMovie, actual);
        assertEquals(expectedMovie, actualMovie);
        assertEquals(expectedMovie.size(), actualMovie.size());
    }
}
