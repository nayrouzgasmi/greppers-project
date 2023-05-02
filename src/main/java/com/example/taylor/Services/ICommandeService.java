package com.example.taylor.Services;

import com.example.taylor.entities.Client;
import com.example.taylor.entities.Commande;

public interface ICommandeService {
    Commande addcommande(Commande c, Long idclient);

    public void deletecommande(Long idCommande);

    Commande updatecommande(Commande c) ;


}
