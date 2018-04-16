package pl.mh.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mh.bookstore.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
