package pl.mh.bookstore.service;

import org.springframework.data.domain.Page;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.Review;
import pl.mh.bookstore.dto.ReviewDto;

public interface ReviewService {
    void save(ReviewDto reviewDto, Book book);
    Page<Review> getPageOfReviews(Integer pageNumber, Book book);
}