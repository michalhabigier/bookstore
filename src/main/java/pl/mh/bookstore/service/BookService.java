package pl.mh.bookstore.service;


import org.springframework.data.domain.Page;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.enums.BookCategory;
import pl.mh.bookstore.dto.BookDto;

public interface BookService {
    Book save(BookDto bookDto);
    Book editBook(Book book, BookDto bookDto);
    Page<Book> viewBooksByCategory(Integer pageNumber, BookCategory bookCategory);
    Page<Book> viewBooks(Integer pageNumber);
}