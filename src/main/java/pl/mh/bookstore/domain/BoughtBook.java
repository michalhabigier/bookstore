package pl.mh.bookstore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BoughtBook {
    @Id
    @GeneratedValue
    private long id;

    private int amount;

    private BigDecimal price;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Order order;
}
