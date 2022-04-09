package com.movieland.cinema.service.impl;

import com.movieland.cinema.entity.User;
import com.movieland.cinema.repository.UserRepository;
import com.movieland.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

//    @Override
//    public Optional<User> getByNickName(String name) {
//        return userRepository.findByNickname(name);
//    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
