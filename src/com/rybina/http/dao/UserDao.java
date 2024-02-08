package com.rybina.http.dao;

import com.rybina.http.entity.Gender;
import com.rybina.http.entity.Role;
import com.rybina.http.entity.User;
import com.rybina.http.util.ConnectionManager;
import com.rybina.http.util.LocalDateFormatter;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    public static final UserDao INSTANCE = new UserDao();

    public static final String SAVE_SQL = """
            insert into users (name, birthday, email, password, role, gender, image) VALUES (?, ?, ?, ?, ?, ?, ?)""";

    public static final String LOGIN_SQL = """
            select id, name, birthday, email, password, role, gender, image from users where email = ? and password = ?""";

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_SQL)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return buildUser(resultSet);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getRole().name());
            preparedStatement.setObject(6, entity.getGender().name());
            preparedStatement.setObject(7, entity.getImage());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();

            entity.setId(generatedKeys.getObject("id", Integer.class));

            return entity;
        }
    }

    private static Optional<User> buildUser(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = User.builder()
                    .id(resultSet.getObject("id", Integer.class))
                    .name(resultSet.getString("name"))
                    .image(resultSet.getString("image"))
                    .role(Role.valueOf(resultSet.getString("role")))
                    .email(resultSet.getString("email"))
                    .password(resultSet.getString("password"))
                    .birthday(resultSet.getDate("birthday").toLocalDate())
                    .gender(Gender.valueOf(resultSet.getString("gender")))
                    .build();
        }
        return Optional.ofNullable(user);
    }
}
