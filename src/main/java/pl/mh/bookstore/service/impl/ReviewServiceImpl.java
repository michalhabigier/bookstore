package pl.mh.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.mh.bookstore.constants.Constants;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.Review;
import pl.mh.bookstore.dto.ReviewDto;
import pl.mh.bookstore.repository.ReviewRepository;
import pl.mh.bookstore.service.ReviewService;
import pl.mh.bookstore.service.UserService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final UserService userService;
    private final ReviewRepository reviewRepository;

    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    public ReviewServiceImpl(UserService userService, ReviewRepository reviewRepository) {
        this.userService = userService;
        this.reviewRepository = reviewRepository;
    }

    public void save(ReviewDto reviewDto, Book book) throws RuntimeException{
        Review review = new Review();
        if(reviewDto.getRate()!=null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            review.setRate(reviewDto.getRate());
            review.setText(reviewDto.getText());
            review.setUser(userService.currentUser());
            review.setDate(reviewDto.getDate());
            review.setBook(book);
            review.setAuthor(username);
            log.debug("Review of {} has been added successfully by user {}", book.getTitle(), username);
            reviewRepository.save(review);
        }
        else throw new RuntimeException("Rate book to give full opinion");
    }

    @Override
    public Page<Review> getPageOfReviews(Integer pageNumber, Book book) {
        PageRequest request = new PageRequest(pageNumber, Constants.REVIEW_PER_PAGE);
        log.debug("Retrieving page of reviews of {}", book.getTitle());
        return reviewRepository.findAllByBook(request, book);
    }
}