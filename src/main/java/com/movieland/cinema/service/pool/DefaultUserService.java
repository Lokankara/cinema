package com.movieland.cinema.service.pool;

import com.movieland.cinema.domain.User;
import com.movieland.cinema.repository.UserRepository;
import com.movieland.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Iterable<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }
}
