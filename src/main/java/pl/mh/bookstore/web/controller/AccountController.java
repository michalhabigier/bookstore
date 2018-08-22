package pl.mh.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mh.bookstore.domain.Address;
import pl.mh.bookstore.service.UserService;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminPanel() {
        return "admin";
    }

    @GetMapping("/account")
    public String accountPage(){
        return "account";
    }

    @GetMapping("/account/addAddress")
    public String address(Model model){
        model.addAttribute("newAddress", new Address());
        return "addressForm";
    }

    @PostMapping
    public String addAddress(@ModelAttribute("newAddress") Address address, BindingResult result){
        if(result.hasErrors())
            return "addressForm";

        userService.addAddress();
        return "redirect:/account";
    }
}
