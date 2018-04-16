package pl.mh.bookstore.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.mh.bookstore.domain.User;
import pl.mh.bookstore.dto.UserDto;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    User save(UserDto userDto);
    Collection<User> findAllUsers();
    User findByLogin(String login);
    User findByEmail(String email);
    void deleteUser(User user);
    User currentUser();
    void addAddress();
}
