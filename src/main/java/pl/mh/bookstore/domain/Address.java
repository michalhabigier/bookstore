package pl.mh.bookstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
@Getter
@Setter
public class Address {
    private String zipCode;
    private String locality;
    private String address;
}
