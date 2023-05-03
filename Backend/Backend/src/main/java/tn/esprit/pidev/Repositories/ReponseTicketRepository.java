package tn.esprit.pidev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev.Entities.ReponseTicket;

import java.util.List;

public interface ReponseTicketRepository extends JpaRepository<ReponseTicket, Integer> {

    List<ReponseTicket> findByStatus(Boolean status);

    Integer countByStatus(Boolean status);

}
