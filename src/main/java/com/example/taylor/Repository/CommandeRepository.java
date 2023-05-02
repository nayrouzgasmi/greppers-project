package com.example.taylor.Repository;

import com.example.taylor.entities.Commande;
import com.example.taylor.entities.Panier;
import com.example.taylor.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository  extends JpaRepository<Commande, Long> {

}
