package pl.mh.bookstore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String zipCode;
    private String locality;
    private String address;
}
