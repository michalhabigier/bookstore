package pl.mh.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mh.bookstore.service.OrderService;

@Controller
public class ShoppingCartController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        model.addAttribute("products", orderService.boughtBooks());
        model.addAttribute("total", orderService.getTotal().toString());
        return "shoppingCart";
    }

    @GetMapping("/books/{bookId}/addProduct")
    public String addProductToCart(@PathVariable("bookId") Long bookId) {
        orderService.addBook(bookId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/removeProduct/{bookId}")
    public String removeProductFromCart(@PathVariable("bookId") Long bookId, Model model) {
        //shoppingCartService.removeBook(bookId);
        //model.addAttribute("products", shoppingCartService.getBooksInCart());
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/checkout")
    public String checkout(Model model) {
        orderService.checkout();
        return "redirect:/shoppingCart";
    }
}

