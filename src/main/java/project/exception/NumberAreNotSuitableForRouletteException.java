package project.exception;

public class NumberAreNotSuitableForRouletteException extends RuntimeException {

    public NumberAreNotSuitableForRouletteException(String message) {
        super(message);
    }

    public NumberAreNotSuitableForRouletteException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberAreNotSuitableForRouletteException(Throwable cause) {
        super(cause);
    }
}
