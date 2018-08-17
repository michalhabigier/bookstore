package pl.mh.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.BoughtBook;
import pl.mh.bookstore.domain.Order;
import pl.mh.bookstore.domain.enums.OrderStatus;
import pl.mh.bookstore.repository.BookRepository;
import pl.mh.bookstore.repository.BoughtBookRepository;
import pl.mh.bookstore.repository.OrderRepository;
import pl.mh.bookstore.service.OrderService;
import pl.mh.bookstore.service.UserService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderServiceImpl implements OrderService {

    private Set<BoughtBook> boughtBooks = new HashSet<>();
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final BoughtBookRepository boughtBookRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    public OrderServiceImpl(BoughtBookRepository boughtBookRepository, BookRepository bookRepository, OrderRepository orderRepository) {
        this.boughtBookRepository = boughtBookRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void addBook(long bookId) {
        BoughtBook boughtBook = new BoughtBook();
        final Book book = bookRepository.findById(bookId);
        boughtBook.setAmount(1);
        boughtBook.setBook(book);
        boughtBook.setPrice(book.getPrice());
        boughtBooks.add(boughtBook);
    }

    @Override
    public void removeBook(BoughtBook boughtBook) {
        boughtBooks.remove(boughtBook);
    }

    @Override
    public BigDecimal getTotal() {
        return boughtBooks
                .stream()
                .map(BoughtBook::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Set<BoughtBook> boughtBooks() {
        return Collections.unmodifiableSet(boughtBooks);
    }

    @Transactional
    public void checkout(){
        Order order = new Order();
        log.debug("Creating new order");
        order.setLocalDate(LocalDate.now());
        order.setUser(userService.currentUser());
        order.setBoughtBooks(boughtBooks);
        order.setTotalCost(getTotal());
        order.setOrderStatus(OrderStatus.NOT_PAID);
        boughtBooks().forEach(o -> o.setOrder(order));
        boughtBookRepository.save(boughtBooks());
        log.debug("Books have been successfully added to an order");
        orderRepository.save(order);
        log.debug("Order with {} ID has been successfully added to database", order.getId());
        boughtBooks.clear();
    }

}
