package pl.mh.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
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

    private MultipartFile bookImage;

    @Length(max = 5000)
    private String description;
}
