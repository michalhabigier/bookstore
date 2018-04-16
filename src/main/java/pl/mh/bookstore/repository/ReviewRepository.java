package pl.mh.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.Review;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
    Page<Review> findAllByBook(Pageable pageable, Book book);
}
