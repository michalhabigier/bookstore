package pl.mh.bookstore.service;

import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.exception.NotEnoughProductsInStockException;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    void addBook(Book book);

    void removeBook(Book book);

    Map<Book, Integer> getBooksInCart();

    BigDecimal getTotal();

    void checkout() throws NotEnoughProductsInStockException;
}
