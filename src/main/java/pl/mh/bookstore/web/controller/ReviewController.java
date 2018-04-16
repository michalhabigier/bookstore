package pl.mh.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.mh.bookstore.configuration.EnumConverter;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.Review;
import pl.mh.bookstore.domain.User;
import pl.mh.bookstore.domain.enums.BookCategory;
import pl.mh.bookstore.dto.ReviewDto;
import pl.mh.bookstore.service.BookService;
import pl.mh.bookstore.service.ReviewService;

import javax.validation.Valid;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class ReviewController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/books/{id}")
    public String rate(@PathVariable("id") Long id, @ModelAttribute("review") @Valid ReviewDto reviewDto, BindingResult result){
        Book book = bookService.findById(id);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        reviewDto.setAuthor(username);
        reviewDto.setBook(book);
        reviewService.save(reviewDto, book);
        if(result.hasErrors()) return "bookDetails";
        return "redirect:/books/{id}";
    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable("id")Long id, Model model, @RequestParam(defaultValue = "0") Integer page){
        Book book = bookService.findById(id);
        model.addAttribute("bookDetails", book);
        model.addAttribute("review", new ReviewDto());
        model.addAttribute("reviews", reviewService.getPageOfReviews(page, book));
        return "bookDetails";
    }

}
