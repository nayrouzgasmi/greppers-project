package com.example.taylor.Repository;

import com.example.taylor.entities.Codepromo;
import com.example.taylor.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CodepromoRepository  extends JpaRepository<Codepromo, Long> {
    Codepromo findByCode(String code);
}
