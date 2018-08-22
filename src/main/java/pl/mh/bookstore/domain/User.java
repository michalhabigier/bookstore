package pl.mh.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDate createdDate;

    private String cardNumber;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    private Set<Payment> paymentsList = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}