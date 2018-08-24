package pl.mh.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mh.bookstore.domain.BoughtBook;

import java.util.List;

public interface BoughtBookRepository extends JpaRepository<BoughtBook, Long>{
    List<BoughtBook> findAllByOrderId(long orderId);
}
