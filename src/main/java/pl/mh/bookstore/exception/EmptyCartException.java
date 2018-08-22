package pl.mh.bookstore.exception;

public class EmptyCartException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "The order is empty!";

    public EmptyCartException() {
        super(DEFAULT_MESSAGE);
    }
}
