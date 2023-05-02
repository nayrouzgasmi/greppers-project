package com.example.taylor.Controllers;
import com.example.taylor.Repository.PanierRepository;
import com.example.taylor.Services.ICommandeService;
import com.example.taylor.Services.IPanierService;
import com.example.taylor.entities.Commande;
import com.example.taylor.entities.Panier;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/commande")
public class CommandeControllers {
    @Autowired
    ICommandeService commandeController;

    // http://localhost:8081/Ecommerce/commande/add-commande/idclient
    @PostMapping("/add-commande/{idclient}")
    public Commande addcommande(@RequestBody Commande c,@PathVariable("idclient") Long idclient) {
        Commande commande = commandeController.addcommande(c,idclient);
        return commande;
    }
    // http://localhost:8081/Ecommerce/commande/remove-commande /{idCommande}
    @DeleteMapping("remove-commande / {idCommande}")
    public void  deletecommande(@PathVariable("idCommande") Long idCommande){
        commandeController.deletecommande(idCommande);
    }

    @PutMapping("/update-commande")
    public Commande updatecommande( @RequestBody Commande c ){
        Commande commande = commandeController.updatecommande(c);
        return commande ;
    }

}
