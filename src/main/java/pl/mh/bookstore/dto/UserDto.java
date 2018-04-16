package pl.mh.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    private void passwordCheck(){
         if(this.password==null | this.passwordConfirm==null) return;
         else if(!this.password.equals(passwordConfirm)){
             this.password=null;
         }
    }

    private void emailCheck(){
        if(this.email==null | this.emailConfirm==null) return;
        else if(!this.email.equals(emailConfirm)){
            this.emailConfirm=null;
        }
    }
}