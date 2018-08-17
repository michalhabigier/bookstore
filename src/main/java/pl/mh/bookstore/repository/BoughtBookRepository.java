package pl.mh.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mh.bookstore.domain.BoughtBook;

public interface BoughtBookRepository extends JpaRepository<BoughtBook, Long>{

}
