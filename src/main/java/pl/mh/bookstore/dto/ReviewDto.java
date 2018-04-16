package pl.mh.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import pl.mh.bookstore.domain.Book;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ReviewDto {
    @Min(1)
    @Max(5)
    @NotNull
    private Double rate;

    @NotEmpty
    private String text;

    private String author;

    private Book book;

    private LocalDate date = LocalDate.now();
}
