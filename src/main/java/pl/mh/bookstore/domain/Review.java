package pl.mh.bookstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Double rate;

    private String text;

    private LocalDate date;

    private String author;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;
}