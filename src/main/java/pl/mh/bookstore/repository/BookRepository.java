package pl.mh.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.enums.BookCategory;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findById(long id);
    Book findBookByAuthorAndTitle(String title, String author);
    Page<Book> findAllByBookCategory(Pageable pageable, BookCategory bookCategory);
    List<Book> findFirst3ByOrderByIdDesc();

    @Modifying
    @Query("update Book b set b.quantity = b.quantity+ :amount where b.id = :bookId")
    void incrementBookAmount(@Param("bookId") long bookId, @Param("amount") int amount);

    @Modifying
    @Query("update Book b set b.quantity = b.quantity-1 where b.id = :bookId")
    void decrementBookAmount(@Param("bookId") long bookId);

    @Transactional
    @Modifying
    @Query("update Book b set b.price = b.price/:vat")
    void noVatPromotion(@Param("vat") BigDecimal vat);

    @Transactional
    @Modifying
    @Query("update Book b set b.price = b.price/:discount where b.bookCategory = :bookCategory")
    void specifiedBookCategoryPromotion(@Param("bookCategory") BookCategory bookCategory, @Param("discount") BigDecimal discount);
}


