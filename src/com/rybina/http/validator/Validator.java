package com.rybina.http.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
