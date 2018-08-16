package pl.mh.bookstore.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class PaymentController {


    @GetMapping("/payment")
    public String paymentDetails(){
        return "payment";
    }

    @GetMapping(value = "/payment/confirm", params = "shippingOption")
    public String completePurchase(@RequestParam ("shippingOption")BigDecimal shippingCost){
        //paymentService.completePayment(shippingCost);
        return "redirect:/books";
    }

}
