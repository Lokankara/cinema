package com.movieland.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
class MovieControllerTest {

    private final WebApplicationContext applicationContext;
    private MockMvc mockMvc;
    String uri = "/api/v1";

    public MovieControllerTest(WebApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    @DisplayName(value = "Test Find All Movies")
    void testFindAllMovies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                        uri + "/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

//    @Test
//    @DisplayName(value = "Test Find Random Movies")
//    void testFindRandomMovies() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(
//                        uri + "/movie/random"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }

    @Test
    @DisplayName(value = "Test Find Movies By Genre Id")
    void testFindMoviesByGenreId() throws Exception {
        int genreId = 3905;
        mockMvc.perform(MockMvcRequestBuilders.get(
                        uri + "/movie/genre/{genreId}", genreId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName(value = "Test Sorted By Rating Asc Founded Movies")
    void testSortedByRatingAscFoundedMovies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                        uri + "/movie?rating=ASC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName(value = "Test Sorted By Rating Desc Founded Movies")
    void testSortedByRatingDescFoundedMovies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                        uri + "/movie?rating=DESC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName(value = "Test Sorted By Price Asc Founded Movies")
    void testSortedByPriceAscFoundedMovies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                        uri + "/movie?price=ASC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName(value = "Test Sorted By Price Desc Founded Movies")
    void testSortedByPriceDescFoundedMovies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                        uri + "/movie?price=DESC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName(value = "Test Find Movie By Id")
    void testFindMovieById() throws Exception {
        int id = 3794;
        mockMvc.perform(MockMvcRequestBuilders.get(
                        uri + "/movie/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}