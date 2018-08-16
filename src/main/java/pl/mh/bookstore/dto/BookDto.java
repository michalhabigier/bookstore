package pl.mh.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import pl.mh.bookstore.domain.enums.BookCategory;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class BookDto {
    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotNull
    private BigDecimal price;

    private Integer quantity;

    private Double rate;

    private BookCategory bookCategory;

    @Length(max = 5000)
    private String description;

    public BookDto(String title, String author, BigDecimal price, Integer quantity, Double rate, BookCategory bookCategory, String description) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.rate = rate;
        this.bookCategory = bookCategory;
        this.description = description;
    }

    public BookDto() {
    }
}
