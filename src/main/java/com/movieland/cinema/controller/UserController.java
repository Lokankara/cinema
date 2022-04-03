package com.movieland.cinema.controller;

import com.movieland.cinema.domain.User;
import com.movieland.cinema.service.pool.DefaultUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final DefaultUserService defaultUserService;

    @GetMapping()
    public Iterable<User> getAll() {
        return defaultUserService.getAllUsers();
    }
}
