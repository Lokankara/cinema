package com.movieland.cinema.dao.jdbc.mapper;

import com.movieland.cinema.entity.User;
import com.movieland.cinema.entity.dto.UserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDtoMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}