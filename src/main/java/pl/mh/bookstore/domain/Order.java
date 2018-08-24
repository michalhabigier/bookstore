package pl.mh.bookstore.domain;

import lombok.Getter;
import lombok.Setter;
import pl.mh.bookstore.domain.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "order")
    private Set<BoughtBook> boughtBooks;

    private LocalDate localDate;

    @ManyToOne
    private User user;

    private BigDecimal totalCost;

    private BigDecimal shippingCost;

    private OrderStatus orderStatus;

    private Address shipmentAddress;
}
