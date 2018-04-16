package pl.mh.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.Review;
import pl.mh.bookstore.domain.User;
import pl.mh.bookstore.dto.ReviewDto;
import pl.mh.bookstore.repository.ReviewRepository;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserService userService;

    private static final int PAGE_SIZE = 10;


    public Review save(ReviewDto reviewDto, Book book) throws RuntimeException{
        Review review = new Review();
        if(reviewDto.getRate()!=null) {
            review.setRate(reviewDto.getRate());
            review.setText(reviewDto.getText());
            review.setUser(userService.currentUser());
            review.setDate(reviewDto.getDate());
            review.setBook(book);
            review.setAuthor(reviewDto.getAuthor());
            return reviewRepository.save(review);
        }
        else throw new RuntimeException("Rate book to give full opinion");

    }

    @Override
    public Page<Review> getPageOfReviews(Integer pageNumber, Book book) {
        PageRequest request = new PageRequest(pageNumber, PAGE_SIZE);
        return reviewRepository.findAllByBook(request, book);
    }
}
