package pl.mh.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pl.mh.bookstore.domain.Payment;
import pl.mh.bookstore.exception.NotEnoughProductsInStockException;
import pl.mh.bookstore.service.BookService;
import pl.mh.bookstore.service.PaymentServiceImpl;
import pl.mh.bookstore.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private BookService bookService;

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getBooksInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/books/{bookId}/addProduct")
    public String addProductToCart(@PathVariable("bookId") Long bookId) {
        shoppingCartService.addBook(bookService.findById(bookId));
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/removeProduct/{bookId}")
    public String removeProductFromCart(@PathVariable("bookId") Long bookId) {
        shoppingCartService.removeBook(bookService.findById(bookId));
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/checkout")
    public String checkout(Model model) {
        try {
            shoppingCartService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            model.addAttribute("exception", new NotEnoughProductsInStockException());
        }
        return "payment";
    }
}

