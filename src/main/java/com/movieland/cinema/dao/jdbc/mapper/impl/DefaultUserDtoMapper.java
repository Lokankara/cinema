package com.movieland.cinema.dao.jdbc.mapper.impl;

import com.movieland.cinema.dao.jdbc.mapper.UserDtoMapper;
import com.movieland.cinema.entity.User;
import com.movieland.cinema.entity.dto.UserDto;

public class DefaultUserDtoMapper implements UserDtoMapper {
    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setNickname(user.getNickname());
        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setNickname(userDto.getNickname());
        return user;
    }
}
