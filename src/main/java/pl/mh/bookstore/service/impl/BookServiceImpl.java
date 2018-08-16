package pl.mh.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.mh.bookstore.constants.Constants;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.enums.BookCategory;
import pl.mh.bookstore.dto.BookDto;
import pl.mh.bookstore.repository.BookRepository;
import pl.mh.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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
            log.debug("Book with title {} of author {} has been added successfully added to shop database{}", bookDto.getTitle(), bookDto.getAuthor());
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
        log.debug("Book with title {} of author {} has been added successfully edited in shop database{}", bookDto.getTitle(), bookDto.getAuthor());
        return bookRepository.save(book);
    }

    @Override
    public Page<Book> viewBooks(Integer pageNumber){
        log.debug("Retrieving page of books");
        return bookRepository.findAll(new PageRequest(pageNumber, Constants.BOOKS_PER_PAGE));
    }

    @Override
    public Page<Book> viewBooksByCategory(Integer pageNumber, BookCategory bookCategory) {
        PageRequest pageRequest = new PageRequest(pageNumber, Constants.BOOKS_PER_PAGE);
        log.debug("Retrieving page of books by {} category", bookCategory.toString());
        return bookRepository.findAllByBookCategory(pageRequest, bookCategory);
    }

}