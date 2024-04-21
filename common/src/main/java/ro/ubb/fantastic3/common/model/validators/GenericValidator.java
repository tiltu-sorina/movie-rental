package ro.ubb.fantastic3.common.model.validators;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.ubb.fantastic3.common.model.Movie;

import javax.validation.*;
import java.util.Set;
@Component
@RequiredArgsConstructor
public class GenericValidator<T> {


    public void validate(T t) {
        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

    }
}
