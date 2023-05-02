package com.example.taylor.Services;

import com.example.taylor.entities.Client;
import com.example.taylor.entities.Produit;

import java.util.List;

public interface Iclientserivce {
    public List<Client> GetAllclient();
    public Client createclient(Client client);

}
