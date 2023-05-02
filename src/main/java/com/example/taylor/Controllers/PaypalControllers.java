package com.example.taylor.Controllers;

import com.example.taylor.Repository.PanierRepository;
import com.example.taylor.Services.IPayPalService;
import com.example.taylor.entities.Commande;
import com.example.taylor.entities.Panier;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
@AllArgsConstructor
//@RequestMapping("/paypal")
public class PaypalControllers {
    @Autowired
    IPayPalService PayPalService;
    PanierRepository panierRepository;

    public static final String SUCCESS_URL = "/success";
    public static final String CANCEL_URL = "/cancel";

    @GetMapping("/")
    public String home() {
        return "home";
    }
    //http://localhost:8081/Ecommerce/pay'

    @PostMapping("/pay")
    public String payment( @RequestParam("total") Double total,
                           @RequestParam("currency") String currency,
                           @RequestParam("method") String method,
                           @RequestParam("intent") String intent,
                           @RequestParam("description") String description) {
        try {
            Payment payment = PayPalService.createPayment(total, currency, method,
                    intent, description , "http://localhost:8081/Ecommerce/pay" + CANCEL_URL,
                    "http://localhost:8081/Ecommerce/pay" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
           }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";

    }
    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = PayPalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }













}
