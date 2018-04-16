package pl.mh.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mh.bookstore.service.PaymentServiceImpl;

import java.math.BigDecimal;

@Controller
public class PaymentController {
    @Autowired
    PaymentServiceImpl paymentService;

    @GetMapping("/payment")
    public String paymentDetails(){
        return "payment";
    }

    @GetMapping(value = "/payment/confirm", params = "shippingOption")
    public String completePurchase(@RequestParam ("shippingOption")BigDecimal shippingCost){
        paymentService.completePayment(shippingCost);
        return "redirect:/books";
    }

}
