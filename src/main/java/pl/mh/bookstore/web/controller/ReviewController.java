package pl.mh.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.dto.ReviewDto;
import pl.mh.bookstore.repository.BookRepository;
import pl.mh.bookstore.service.ReviewService;

import javax.validation.Valid;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class ReviewController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/books/{id}")
    public String rate(@PathVariable("id") Long id, @ModelAttribute("review") @Valid ReviewDto reviewDto, BindingResult result){
        Book book = bookRepository.findById(id);
        if(result.hasErrors()) return "bookDetails";
        reviewService.save(reviewDto, book);
        return "redirect:/books/{id}";
    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable("id")long bookId, Model model, @RequestParam(defaultValue = "0") Integer page){
        Book book = bookRepository.findById(bookId);
        model.addAttribute("bookDetails", book);
        model.addAttribute("review", new ReviewDto());
        model.addAttribute("reviews", reviewService.getPageOfReviews(page, book));
        return "bookDetails";
    }

}
