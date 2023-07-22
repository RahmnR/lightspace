package com.enigma.lightspace.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;
@Component
@RequiredArgsConstructor
public class ValidationUtil {
    private final Validator validate;

    public <T> void validate(T obj) {
        Set<ConstraintViolation<T>> result = validate.validate(obj);
        if (result.size() != 0) {
            throw new ConstraintViolationException(result);
        }
    }

}
