package pl.mh.bookstore.service;

import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.exception.NotEnoughProductsInStockException;

import java.math.BigDecimal;
import java.util.Set;

public interface ShoppingCartService {

    void addBook(long bookId);

    void removeBook(long bookId);

    Set<Book> getBooksInCart();

    BigDecimal getTotal();

    void checkout() throws NotEnoughProductsInStockException;
}