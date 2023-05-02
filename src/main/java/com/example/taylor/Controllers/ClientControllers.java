package com.example.taylor.Controllers;
import com.example.taylor.Repository.PanierRepository;
import com.example.taylor.Services.IPanierService;
import com.example.taylor.Services.IProduitService;
import com.example.taylor.Services.Iclientserivce;
import com.example.taylor.entities.Client;
import com.example.taylor.entities.Panier;
import com.example.taylor.entities.Produit;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")

public class ClientControllers {
    @Autowired
    Iclientserivce iclientserivce;
    //http://localhost:8081/Ecommerce/client/Getclient
    @GetMapping("Getclient")
    public List<Client> GetAllproduit() {
        List<Client> client = iclientserivce.GetAllclient();
        return client;
    }
    //http://localhost:8081/Ecommerce/client/addclient
    @PostMapping("addclient")
    public Client createProduct(@RequestBody Client client) {
        return  iclientserivce.createclient(client);
    }
}
