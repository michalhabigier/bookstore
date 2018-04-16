package pl.mh.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.enums.BookCategory;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Book findById(long id);

    Book findBookByAuthorAndTitle(String title, String author);

    Page<Book> findAllByBookCategory(Pageable pageable, BookCategory bookCategory);
}


