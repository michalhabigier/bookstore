package pl.mh.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import pl.mh.bookstore.configuration.EnumConverter;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.enums.BookCategory;
import pl.mh.bookstore.dto.BookDto;
import pl.mh.bookstore.repository.BookRepository;

import java.util.Set;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    private static final int PAGE_SIZE = 12;

    public Book save(BookDto bookDto) throws RuntimeException{

        if(bookRepository.findBookByAuthorAndTitle(bookDto.getAuthor(), bookDto.getTitle())==null) {
            Book book = new Book();
            book.setAuthor(bookDto.getAuthor());
            book.setTitle(bookDto.getTitle());
            book.setPrice(bookDto.getPrice());
            book.setBookCategory(bookDto.getBookCategory());
            book.setRate(0.0);
            book.setQuantity(bookDto.getQuantity());
            book.setDescription(bookDto.getDescription());
            return bookRepository.save(book);
        }
        else throw new RuntimeException("This book already exists in database");
    }

    @Override
    public Book editBook(Book book, BookDto bookDto) {
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        book.setBookCategory(bookDto.getBookCategory());
        book.setQuantity(bookDto.getQuantity());
        return bookRepository.save(book);
    }

    @Override
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> viewBooks(Integer pageNumber){
        return bookRepository.findAll(new PageRequest(pageNumber, PAGE_SIZE));
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Page<Book> viewBooksByCategory(Integer pageNumber, BookCategory bookCategory) {
        PageRequest pageRequest = new PageRequest(pageNumber, PAGE_SIZE);
        return bookRepository.findAllByBookCategory(pageRequest, bookCategory);
    }

}
