package tn.esprit.pidev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev.Entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
