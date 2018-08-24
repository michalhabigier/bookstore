package pl.mh.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mh.bookstore.domain.Order;
import pl.mh.bookstore.domain.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findAllByUser(User user);
}
