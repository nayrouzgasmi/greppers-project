package com.example.taylor.Services;

import com.example.taylor.entities.Commande;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class EmailconfirmationService implements IEmailconfirmationService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmailConfirmation(Commande commande) throws MessagingException {

        // Create the email body using the order details
        String emailBody = "Bonjour " + commande.getNomclient() + ",\n\n"
                + "Nous vous remercions pour votre commande sur notre site e-commerce. Voici les détails de votre commande:\n\n"
                + "Coût total: " + commande.getTotalPrice() + "\n\n"
                + "Merci d'avoir choisi notre site e-commerce!\n\n"
                + "Cordialement,\n"
                + "L'équipe de notre site e-commerce";

        // Create the email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("votre-adresse-email@gmail.com");
        message.setTo(commande.getEmailclient());
        message.setSubject("Confirmation de commande sur notre site e-commerce");
        message.setText(emailBody);

        // Send the email
        mailSender.send(message);
    }
}
