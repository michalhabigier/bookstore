package pl.mh.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mh.bookstore.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
