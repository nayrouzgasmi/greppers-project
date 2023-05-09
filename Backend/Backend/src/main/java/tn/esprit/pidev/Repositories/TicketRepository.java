package tn.esprit.pidev.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Ticket;
import tn.esprit.pidev.Entities.TicketStatus;

import java.util.List;

public interface TicketRepository  extends JpaRepository<Ticket, Integer> {

    Integer countByProduct(Product product);
    List<Ticket> findByStatus(TicketStatus status);

    Integer countByStatus(TicketStatus status);
}
