package tn.esprit.pidev.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.pidev.Entities.Client;

public interface ClientRepository  extends JpaRepository<Client, Long> {
}
