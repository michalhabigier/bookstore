package pl.mh.bookstore.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Double rate;

    private String text;

    protected LocalDate date;

    private String author;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;
}
