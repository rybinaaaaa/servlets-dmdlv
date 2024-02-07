package com.rybina.http.validator;

import com.rybina.http.dto.CreateUserDto;
import com.rybina.http.entity.Gender;
import com.rybina.http.util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {
    public static final CreateUserValidator INSTANCE = new CreateUserValidator();

    private CreateUserValidator() {
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        ValidationResult validationResult = new ValidationResult();

        if (!LocalDateFormatter.isValid(object.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }

        if (object.getName().isEmpty()) {
            validationResult.add(Error.of("invalid.name", "Name is invalid"));
        }

        try {
            Gender.valueOf(object.getGender());
        } catch (RuntimeException e) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        return validationResult;
    }
}
