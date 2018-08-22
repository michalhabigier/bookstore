package pl.mh.bookstore.exception;

public class NotEnoughProductsInStockException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }


}
