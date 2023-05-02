package com.example.taylor.Controllers;

import com.example.taylor.Repository.PanierRepository;
import com.example.taylor.Services.IPanierService;
import com.example.taylor.entities.Client;
import com.example.taylor.entities.Panier;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
@AllArgsConstructor
@RequestMapping("/panier")
public class PanierControllers {
    @Autowired
    IPanierService PanierController;


    // http://localhost:8081/Ecommerce/panier/add-panier/{idproduit}/idclient
    @PostMapping("/add-panier/{idproduit}/{idclient}")
    //Panier
    public Panier addPanier(@RequestBody Panier p, HttpSession session, @PathVariable("idproduit") Long idproduit, @PathVariable("idclient") Long idclient) {
        Client client = (Client) session.getAttribute("client");
        Panier panier = PanierController.getOrCreatePanierForClient(session, client);
        Panier lignePanier = PanierController.addPanier(p, idproduit, idclient);
        //return lignePanier;
        return lignePanier;
    }
    // http://localhost:8081/Ecommerce/panier/GetPanier

    @GetMapping("/GetPanier")
    public List<Panier> getAllPaniers() {
        List<Panier> panier = PanierController.GetAllpanier();
        return panier;
    }

    @GetMapping("/GetPanier/{idclient}")
    public List<Panier> getPanierByClientId(@PathVariable Long idclient) {
        return PanierController.getPanierByClientId(idclient);
    }

    // http://localhost:8081/Ecommerce/panier/remove-panier/{idLignePanier}
    @DeleteMapping("remove-panier/{idLignePanier}")
    public void deletePanier(@PathVariable("idLignePanier") Long idLignePanier) {
        PanierController.deletePanier(idLignePanier);
    }

    // http://localhost:8081/Ecommerce/panier/update-panier
    @PutMapping("/update-panier")
    public Panier updatepanier(@RequestBody Panier p) {
        Panier panier = PanierController.updatepanier(p);
        return panier;
    }

    // http://localhost:8081/Ecommerce/panier/apply-code-promo/{code}
    @PutMapping("/apply-code-promo/{code}")
    public Panier applyCodePromo(@RequestBody Panier panier, @PathVariable("code") String code) {
        return PanierController.applyCodePromo(panier, code);

    }


}
