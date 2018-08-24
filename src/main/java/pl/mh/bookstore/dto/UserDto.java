package pl.mh.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {
    @NotEmpty
    private String login;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

    @NotNull(message = "password doesn't match")
    private String passwordConfirm;

    @NotEmpty @Email
    private String email;

    @NotEmpty @Email
    private String emailConfirm;

    @Length(min = 16, max = 16)
    private String cardNumber;
}