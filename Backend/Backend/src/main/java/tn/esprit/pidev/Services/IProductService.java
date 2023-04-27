package tn.esprit.pidev.Services;

import java.util.List;
import java.util.Optional;

import tn.esprit.pidev.Entities.Product;

public interface IProductService {

    List<Product> findAll();

    Optional<Product> findById(long id);

    Product save(Product product);

    Product updateProduct(long id, Product updatedProduct);

    void deleteById(long id);

}