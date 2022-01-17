package by.epam.heritage.ap.repository.connection;

public class PoolException extends Exception {
    public PoolException() {}

    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolException(String message) {
        super(message);
    }

    public PoolException(Throwable cause) {
        super(cause);
    }
}
