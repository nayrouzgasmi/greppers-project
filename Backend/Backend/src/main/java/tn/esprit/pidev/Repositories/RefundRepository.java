package tn.esprit.pidev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev.Entities.Refund;

public interface RefundRepository extends JpaRepository<Refund, Integer> {
}
