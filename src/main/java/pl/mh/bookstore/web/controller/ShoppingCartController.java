package pl.mh.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mh.bookstore.domain.Address;
import pl.mh.bookstore.domain.BoughtBook;
import pl.mh.bookstore.domain.User;
import pl.mh.bookstore.service.OrderService;
import pl.mh.bookstore.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class ShoppingCartController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        model.addAttribute("products", orderService.boughtBooks());
        model.addAttribute("total", orderService.getTotal().toString());
        model.addAttribute("shippingCost", orderService.getShippingCost().toString());
        return "shoppingCart";
    }

    @GetMapping("/books/{bookId}/addProduct")
    public String addProductToCart(@PathVariable("bookId") Long bookId) {
        orderService.addBook(bookId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/removeProduct/{bookId}")
    public String removeProductFromCart(@PathVariable("bookId") Long bookId) {
        orderService.removeBook(bookId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/checkout")
    public String checkout(Model model) {
        orderService.cartContentCheck();
        User user = userService.currentUser();
        if (user.getAddress() == null) {
            model.addAttribute("newAddress", new Address());
        } else
            model.addAttribute("newAddress", user.getAddress());
        return "addressForm";
    }

    @PostMapping("/shoppingCart/checkout")
    public String orderShipping(@ModelAttribute("newAddress") Address address, BindingResult result) {
        if (result.hasErrors())
            return "addressForm";
        else {
            orderService.checkout();
        }
        return "redirect:/shoppingCart/orderDetails";
    }

    @GetMapping("/shoppingCart/orderDetails")
    public String getOrderDetails(Model model) {
        Set<BoughtBook> boughtBooks = new HashSet<>(orderService.boughtBooks());
        model.addAttribute("boughtBooks", boughtBooks);
        model.addAttribute("booksCost", orderService.getTotal().toString());
        model.addAttribute("shippingCost", orderService.getShippingCost().toString());
        model.addAttribute("totalCost", orderService.getTotalWithShippingCost().toString());
        orderService.clear();
        return "orderDetails";
    }
}

