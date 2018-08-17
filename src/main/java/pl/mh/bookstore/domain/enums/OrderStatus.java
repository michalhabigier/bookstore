package pl.mh.bookstore.domain.enums;

public enum OrderStatus {
    SHIPPED("Shipped"),
    NOT_PAID("Accepted"),
    PAID("Fulfilled");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }
}
