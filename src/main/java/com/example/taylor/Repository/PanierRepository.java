package com.example.taylor.Repository;

import com.example.taylor.entities.Client;
import com.example.taylor.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository  extends JpaRepository<Panier, Long> {

    @Query("SELECT p FROM Panier p WHERE p.client = :client")
    Panier findByClient(@Param("client") Client client);

    List<Panier> findByClient_Idclient(Long idclient);






}
