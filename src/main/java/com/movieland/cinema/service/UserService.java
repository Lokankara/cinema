package com.movieland.cinema.service;

import com.movieland.cinema.domain.User;
import com.movieland.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Iterable<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }
}
