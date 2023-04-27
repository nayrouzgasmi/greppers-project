package tn.esprit.pidev.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Tag;
import tn.esprit.pidev.Repositories.ProductRepository;
import tn.esprit.pidev.Repositories.TagRepository;

@Service
public class ProductService implements IProductService {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }
    
    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }
    
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public Product updateProduct(long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            // existingProduct.setStore(updatedProduct.getStore());
            existingProduct.setCompositions(updatedProduct.getCompositions());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setAvailable(updatedProduct.isAvailable());
            existingProduct.setBioScore(updatedProduct.getBioScore());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }
    
    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }


}
