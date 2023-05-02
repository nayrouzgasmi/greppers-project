package com.example.taylor.Controllers;
import com.example.taylor.Repository.PanierRepository;
import com.example.taylor.Services.IPanierService;
import com.example.taylor.Services.IProduitService;
import com.example.taylor.entities.Panier;
import com.example.taylor.entities.Produit;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/produit")
public class ProduitControllers {
    @Autowired
    IProduitService ProduitController;
    //http://localhost:8081/Ecommerce/produit/GetProduit
    @GetMapping("GetProduit")
    public List<Produit> GetAllproduit() {
        List<Produit> produit = ProduitController.GetAllproduit();
        return produit;
    }
    //http://localhost:8081/Ecommerce/produit/addproduit
    @PostMapping("addproduit")
    public Produit createProduct(@RequestBody Produit product) {
        return ProduitController.createProduct(product);
    }
}
