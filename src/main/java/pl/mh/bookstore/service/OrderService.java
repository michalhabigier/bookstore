package pl.mh.bookstore.service;

import pl.mh.bookstore.domain.BoughtBook;

import java.math.BigDecimal;
import java.util.Set;

public interface OrderService {
    void addBook(long bookId);
    void removeBook(long bookId);
    BigDecimal getTotal();
    BigDecimal getTotalWithShippingCost();
    BigDecimal getShippingCost();
    Set<BoughtBook> boughtBooks();
    void checkout();

    void cartContentCheck();
}