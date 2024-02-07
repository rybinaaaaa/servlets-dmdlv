package com.rybina.http.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private List<Error> errors = new ArrayList<>();

    public void add(Error e) {
        this.errors.add(e);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<Error> getErrors() {
        return errors;
    }
}
