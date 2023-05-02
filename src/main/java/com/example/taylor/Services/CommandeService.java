package com.example.taylor.Services;
import com.example.taylor.Repository.ClientRepository;
import com.example.taylor.Repository.CommandeRepository;
import com.example.taylor.Repository.PanierRepository;
import com.example.taylor.entities.Client;
import com.example.taylor.entities.Commande;
import com.example.taylor.entities.Panier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CommandeService implements ICommandeService{
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PanierRepository panierRepository;

    @Override
    public Commande addcommande(Commande c,Long idclient) {
        Client client = clientRepository.findById(idclient).orElse(null);
        Panier panier = panierRepository.findByClient(client);
        double totalPrice = panier.getTotalprice();
        String emailclient = client.getEmail();
        String nomclient = client.getNom();

        c.setClient(client);
        c.setTotalPrice(totalPrice);
        c.setEmailclient(emailclient);
        c.setNomclient(nomclient);

        return commandeRepository.save(c);
    }
    @Override
    public void deletecommande(Long idCommande) {
        commandeRepository.deleteById(idCommande);
    }
    @Override
   public Commande updatecommande(Commande c)  {
        return commandeRepository.save(c);
    }

}

