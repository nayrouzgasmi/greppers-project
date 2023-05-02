package com.example.taylor.Controllers;

import com.example.taylor.Services.EmailconfirmationService;
import com.example.taylor.Services.IEmailconfirmationService;
import com.example.taylor.entities.Commande;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class Emailconfirmationcontrollers {
    @Autowired
    IEmailconfirmationService emailconfirmationService;
    @PostMapping("/send-confirmation")
    public ResponseEntity<String> sendOrderConfirmationEmail(@RequestBody Commande commande) {
        try {

            emailconfirmationService.sendEmailConfirmation(commande);
            return ResponseEntity.ok("Email sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending email: " + e.getMessage());
        }
    }
}
