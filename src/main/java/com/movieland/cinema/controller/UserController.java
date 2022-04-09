package com.movieland.cinema.controller;

import com.movieland.cinema.entity.User;
import com.movieland.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping()
    public Optional<User> getByUsername(
            @RequestParam(defaultValue = "") Long id) {
        return userRepository.findById(id);
    }
}
