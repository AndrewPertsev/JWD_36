package by.epam.heritage.ap.service.validator;

public class ValidatorException extends Exception {
    public ValidatorException() {
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
