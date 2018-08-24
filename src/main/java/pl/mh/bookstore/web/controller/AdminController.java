package pl.mh.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mh.bookstore.constants.Constants;
import pl.mh.bookstore.domain.Book;
import pl.mh.bookstore.domain.User;
import pl.mh.bookstore.domain.enums.BookCategory;
import pl.mh.bookstore.dto.BookDto;
import pl.mh.bookstore.repository.BookRepository;
import pl.mh.bookstore.repository.UserRepository;
import pl.mh.bookstore.service.BookService;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "adminUsersList";
    }

    @GetMapping("/admin/books")
    public String getAllBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "adminBooksList";
    }

    @ModelAttribute("book")
    public BookDto addBookDTO(){
        return new BookDto();
    }

    @GetMapping("/admin/book/add")
    public String showAddBookForm(Model model){
        model.addAttribute("bookCategories", BookCategory.values());
        return "adminProcessBookForm";
    }

    @PostMapping("/admin/book/add")
    public String addBook(@ModelAttribute("book") @Valid BookDto bookDto, BindingResult result){
        if(result.hasErrors()){
            return "adminProcessBookForm";
        }
        bookService.save(bookDto);
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/book/{id}/edit")
    public String showEditBookForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("book", bookRepository.findById(id));
        model.addAttribute("bookCategories", BookCategory.values());
        return "adminProcessBookForm";
    }

    @PostMapping("/admin/book/{id}/edit")
    public String editBook(@Valid BookDto bookDto, @PathVariable("id") Long id, BindingResult result, Model model){
        Book currBook = bookRepository.findById(id);
        model.addAttribute("book", bookService.editBook(currBook, bookDto));
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/users/delete/{login}")
    public String deleteUser(@PathVariable("login") String login, Model model){
        User user = userRepository.findByLogin(login);
        userRepository.delete(user);
        model.addAttribute("usersList", userRepository.findAll());
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/book/delete/{id}")
    public String deleteBook(@PathVariable("id") long bookId, Model model){
        bookRepository.delete(bookId);
        model.addAttribute("booksList", bookRepository.findAll());
        return "redirect:/admin/books";
    }

    @GetMapping(value = "/admin/books/discount", params = "category")
    public String setDiscountForSpecifiedBookType(Model model,
                                                  @RequestParam(name = "category") BookCategory bookCategory) {
        model.addAttribute("bookCategories", BookCategory.values());
        bookRepository.specifiedBookCategoryPromotion(bookCategory, Constants.BOOK_DISCOUNT_BY_10_PERCENT);
        return "redirect:/admin/books";
    }

    @GetMapping(value = "/admin/books/discount")
    public String setNoVatDiscount() {
        bookRepository.noVatPromotion(Constants.VAT);
        return "redirect:/admin/books";
    }

}
