package com.rybina.http.mapper;

import com.rybina.http.dto.CreateUserDto;
import com.rybina.http.dto.UserDto;
import com.rybina.http.entity.Gender;
import com.rybina.http.entity.Role;
import com.rybina.http.entity.User;
import com.rybina.http.util.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    private CreateUserMapper() {
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .build();
    }
}
