package pl.mh.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.enums.BookCategory;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findById(long id);
    Book findBookByAuthorAndTitle(String title, String author);
    Page<Book> findAllByBookCategory(Pageable pageable, BookCategory bookCategory);
    List<Book> findFirst3ByOrderByIdDesc();
}


