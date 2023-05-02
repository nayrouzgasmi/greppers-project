package com.example.taylor.Services;

import com.example.taylor.Repository.CommandeRepository;
import com.example.taylor.Repository.PanierRepository;
import com.example.taylor.Repository.ProduitRepository;
import com.example.taylor.entities.Commande;
import com.example.taylor.entities.Panier;
import com.example.taylor.entities.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProduitService implements IProduitService{

    @Autowired
    ProduitRepository produitRepository ;
    @Override
    public List<Produit> GetAllproduit() {
        return produitRepository.findAll();
    }
    @Override
    public Produit createProduct(Produit product) {
        return produitRepository .save(product);
    }
}
