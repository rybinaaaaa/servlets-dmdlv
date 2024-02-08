package com.rybina.http.mapper;

import com.rybina.http.dto.ReadUserDto;
import com.rybina.http.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadUserMapper implements Mapper<User, ReadUserDto>{

    private static final ReadUserMapper userMapper = new ReadUserMapper();

    public static ReadUserMapper getInstance() {
        return userMapper;
    }


    @Override
    public ReadUserDto mapFrom(User u) {
        return ReadUserDto.builder()
                .id(u.getId())
                .role(u.getRole())
                .gender(u.getGender())
                .name(u.getName())
                .email(u.getEmail())
                .birthday(u.getBirthday())
                .image(u.getImage())
                .build();
    }
}
