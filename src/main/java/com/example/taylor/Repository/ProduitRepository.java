package com.example.taylor.Repository;

import com.example.taylor.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository  extends JpaRepository<Produit, Long> {
}
