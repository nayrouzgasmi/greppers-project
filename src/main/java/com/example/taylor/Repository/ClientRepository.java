package com.example.taylor.Repository;

import com.example.taylor.entities.Client;
import com.example.taylor.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long> {
}
