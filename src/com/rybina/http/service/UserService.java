package com.rybina.http.service;

import com.rybina.http.dao.UserDao;
import com.rybina.http.dto.CreateUserDto;
import com.rybina.http.dto.ReadUserDto;
import com.rybina.http.entity.User;
import com.rybina.http.exception.ValidationException;
import com.rybina.http.mapper.CreateUserMapper;
import com.rybina.http.mapper.ReadUserMapper;
import com.rybina.http.validator.CreateUserValidator;
import com.rybina.http.validator.ValidationResult;
import lombok.SneakyThrows;

import java.util.Optional;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final ReadUserMapper readUserMapper = ReadUserMapper.getInstance();
    private final static String IMAGE_FOLDER = "/users/";

    private UserService() {
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
//        validation
        ValidationResult validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
//        map DTO -> entity
        User user = createUserMapper.mapFrom(userDto);
//        Сохраняем картинку
        imageService.upload(IMAGE_FOLDER + userDto.getImage().getSubmittedFileName(), userDto.getImage().getInputStream());
//        userDao.save
        userDao.save(user);
//        return id
        return user.getId();
    }

    public Optional<ReadUserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password).map(readUserMapper::mapFrom);
    }
}
