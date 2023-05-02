package com.example.taylor.Services;

import com.example.taylor.entities.Commande;

import javax.mail.MessagingException;

public interface IEmailconfirmationService {
    public void sendEmailConfirmation(Commande commande) throws MessagingException;
}
