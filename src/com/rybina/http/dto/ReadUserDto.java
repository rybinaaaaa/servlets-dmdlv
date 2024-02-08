package com.rybina.http.dto;

import com.rybina.http.entity.Gender;
import com.rybina.http.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ReadUserDto {
    String email;
    String name;
    Integer id;
    String image;
    Role role;
    Gender gender;
    LocalDate birthday;
}
