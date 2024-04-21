package ro.ubb.fantastic3.common.model.validators.exceptions;

public class ValidatorException extends RuntimeException{
    public ValidatorException() {
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
