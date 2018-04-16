package pl.mh.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.exception.NotEnoughProductsInStockException;
import pl.mh.bookstore.repository.BookRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private BookRepository bookRepository;

    private Map<Book, Integer> books = new HashMap<>();

    @Override
    public void addBook(Book book) {
        if(books.containsKey(book)){
            books.replace(book, books.get(book)+1);
        }
        else{
            books.put(book, 1);
        }
    }

    @Override
    public void removeBook(Book book) {
        if(books.containsKey(book)){
            if(books.get(book)>1) books.replace(book, books.get(book)-1);
            else if(books.get(book) == 1){
                books.remove(book);
            }
        }
    }

    @Override
    public void checkout() throws NotEnoughProductsInStockException{
        Book book;
        for(Map.Entry<Book, Integer> entry: books.entrySet()){
            book = bookRepository.findById(entry.getKey().getId());
            if(book.getQuantity() < entry.getValue()) throw new NotEnoughProductsInStockException();
            entry.getKey().setQuantity(book.getQuantity() - entry.getValue());
        }
    }

    @Override
    public Map<Book, Integer> getBooksInCart() {
        return Collections.unmodifiableMap(books);
    }

    @Override
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for( Map.Entry<Book, Integer> entry : books.entrySet()){
            total = total.add(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
        }
        return total;
    }
}
