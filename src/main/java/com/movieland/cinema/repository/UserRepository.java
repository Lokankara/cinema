package com.movieland.cinema.repository;

import com.movieland.cinema.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "userRepository")
public interface UserRepository extends CrudRepository<User, Long> {


}