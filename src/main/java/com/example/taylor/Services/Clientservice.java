package com.example.taylor.Services;
import com.example.taylor.Repository.ClientRepository;
import com.example.taylor.Repository.CommandeRepository;
import com.example.taylor.Repository.PanierRepository;
import com.example.taylor.Repository.ProduitRepository;
import com.example.taylor.entities.Client;
import com.example.taylor.entities.Commande;
import com.example.taylor.entities.Panier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class Clientservice implements Iclientserivce {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public List<Client> GetAllclient() {
        return clientRepository.findAll();
    }
    @Override
    public Client createclient(Client client) {
        return clientRepository.save(client);
    }
}
