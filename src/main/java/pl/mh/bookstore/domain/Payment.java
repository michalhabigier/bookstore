package pl.mh.bookstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "bought_books", joinColumns = {@JoinColumn(name = "payment_id")}, inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private Set<Book> books;

    private BigDecimal totalCost;

    private Address address;
}
