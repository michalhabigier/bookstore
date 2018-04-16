package pl.mh.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mh.bookstore.configuration.EnumConverter;
import pl.mh.bookstore.domain.enums.BookCategory;
import pl.mh.bookstore.service.BookService;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String booksPage(Model model, @RequestParam(defaultValue = "0") Integer page){
        model.addAttribute("books", bookService.viewBooks(page));
        model.addAttribute("currentPage", page);
        model.addAttribute("bookCategories", BookCategory.values());
        return "booksList";
    }

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("bestRated", bookService.findAllBooks());
        model.addAttribute("mostPopular", bookService.findAllBooks());
        return "index";
    }

    @GetMapping(value = "/books", params = "category")
    public String booksByCategoryPage(Model model,
                                      @RequestParam(name ="category", required = false) BookCategory bookCategory,
                                      @RequestParam(defaultValue = "0") Integer page){
        model.addAttribute("books", bookService.viewBooksByCategory(page, bookCategory));
        model.addAttribute("bookCategories", BookCategory.values());
        return "booksList";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(BookCategory.class, new EnumConverter<>(BookCategory.class));
    }
}
