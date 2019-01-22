package project.exception;

public class EqualNumbersForRangeException extends RuntimeException {

    public EqualNumbersForRangeException(String message) {
        super(message);
    }

    public EqualNumbersForRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EqualNumbersForRangeException(Throwable cause) {
        super(cause);
    }
}
