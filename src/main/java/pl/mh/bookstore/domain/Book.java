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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (quantity != null ? !quantity.equals(book.quantity) : book.quantity != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (rate != null ? !rate.equals(book.rate) : book.rate != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (reviews != null ? !reviews.equals(book.reviews) : book.reviews != null) return false;
        if (payments != null ? !payments.equals(book.payments) : book.payments != null) return false;
        return bookCategory == book.bookCategory;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
