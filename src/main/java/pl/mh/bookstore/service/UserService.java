package pl.mh.bookstore.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.mh.bookstore.domain.Address;
import pl.mh.bookstore.domain.User;
import pl.mh.bookstore.dto.UserDto;

public interface UserService extends UserDetailsService {
    void save(UserDto userDto);
    User currentUser();

    void addAddress(Address addressDto);
}