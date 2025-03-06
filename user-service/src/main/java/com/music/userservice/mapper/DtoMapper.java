package com.music.userservice.mapper;

import com.music.userservice.dto.UserDto;
import com.music.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DtoMapper {
    public abstract User map(UserDto userDto);
    public abstract UserDto map(User user);
}

