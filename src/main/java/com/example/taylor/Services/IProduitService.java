package com.example.taylor.Services;

import com.example.taylor.entities.Produit;

import java.util.List;

public interface IProduitService {
    public List<Produit> GetAllproduit();
    public Produit createProduct(Produit product);
}
