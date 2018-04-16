package pl.mh.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mh.bookstore.domain.enums.BookCategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book{

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String author;

    private Integer quantity;

    private BigDecimal price;

    private Double rate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    private List<Payment> payments = new ArrayList<>();

    private BookCategory bookCategory;

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
}
