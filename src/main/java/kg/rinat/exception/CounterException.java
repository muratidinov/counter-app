package kg.rinat.exception;

public class CounterException extends RuntimeException {
    private String message;

    public CounterException(String message) {
        super(message);
    }
}
