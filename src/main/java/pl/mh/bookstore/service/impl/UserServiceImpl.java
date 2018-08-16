package pl.mh.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mh.bookstore.domain.Address;
import pl.mh.bookstore.domain.Role;
import pl.mh.bookstore.domain.User;
import pl.mh.bookstore.dto.UserDto;
import pl.mh.bookstore.repository.UserRepository;
import pl.mh.bookstore.service.UserService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public User currentUser() {
       String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug("You are logged as {}", username);
        return userRepository.findByLogin(username);
    }

    @Override
    public void addAddress() {
        User user = currentUser();
        Address address = new Address();
        address.setAddress(address.getAddress());
        address.setLocality(address.getLocality());
        address.setZipCode(address.getZipCode());
        userRepository.save(user);

        log.debug("Address of user {} has been added successfully", user.getLogin());
    }

    public void save(UserDto userDto){
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCardNumber(userDto.getCardNumber());
        user.setCreatedDate(LocalDate.now());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));

        log.debug("User {} has been registered successfully", user.getLogin());

        userRepository.save(user);
    }

}