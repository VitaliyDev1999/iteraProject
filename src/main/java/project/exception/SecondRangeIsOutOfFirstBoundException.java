package project.exception;

public class SecondRangeIsOutOfFirstBoundException extends RuntimeException {

    public SecondRangeIsOutOfFirstBoundException(String message) {
        super(message);
    }

    public SecondRangeIsOutOfFirstBoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecondRangeIsOutOfFirstBoundException(Throwable cause) {
        super(cause);
    }
}
