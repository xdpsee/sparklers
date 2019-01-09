package com.zhenhui.sparkler.data.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("unused")
public final class ValidationUtils {

    private static Validator validator;
    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static <T> boolean isValid(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        return violations.isEmpty();
    }

    public static <T> Set<ConstraintViolation<T>> valid(T object) {
        return validator.validate(object);
    }

    public static <T> void validate(T object) throws IllegalArgumentException {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        Iterator<ConstraintViolation<T>> iterator = violations.iterator();
        if (iterator.hasNext()) {
            ConstraintViolation<T> item = iterator.next();
            throw new IllegalArgumentException(item.getPropertyPath().toString() + "," + item.getMessage());
        }

    }
}

