package ro.ubb.fantastic3.common.model.validators.exceptions;

public class RentedException extends RuntimeException {
    public RentedException() {
    }

    public RentedException(String message) {
        super(message);
    }

    public RentedException(String message, Throwable cause) {
        super(message, cause);
    }
}
