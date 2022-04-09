package com.movieland.cinema.service;

import com.movieland.cinema.entity.User;

import java.util.Optional;

public interface UserService {

//    Optional<User> getByNickName(String name);

    Optional<User> getById(Long id);
}