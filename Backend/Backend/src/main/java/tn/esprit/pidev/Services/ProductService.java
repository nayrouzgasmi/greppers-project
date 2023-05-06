package tn.esprit.pidev.Services;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import tn.esprit.pidev.Entities.Store;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import tn.esprit.pidev.Entities.Composition;
import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Tag;
import tn.esprit.pidev.Repositories.CompositionRepository;
import tn.esprit.pidev.Repositories.ProductRepository;
import tn.esprit.pidev.Repositories.StoreRepository;
import tn.esprit.pidev.Repositories.TagRepository;
import tn.esprit.pidev.Util.ObjectStorage;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    CompositionRepository compositionRepository;
    @Autowired
    IObjectStorageService objectStorageService;
    // @Value("${do.space.bucket}")

    String FOLDER = "products/";

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

    public void deleteProductWithReference(Long productId) {
        // Retrieve the product that you want to delete
        Product product = productRepository.findById(productId).orElse(null);
        // product.getImageUrls().clear();

        if (product != null) {
            // Retrieve the stores that reference the product
            List<Store> stores = storeRepository.findByProducts_Id(productId);

            // Remove the product from the set of products in the stores
            for (Store store : stores) {
                store.getProducts().remove(product);
                storeRepository.save(store);
            }
            // product.getTags().forEach(tag->product.getTags().remove(tag));
            product.getTags().clear();
            product.getImageUrls().forEach(image -> objectStorageService.deleteFile(image));
            product.getImageUrls().clear();
            // product.getImageUrls().forEach(image->product.getImageUrls().remove(image));
            // Delete the product
            productRepository.delete(product);
        }
    }

    public Product createProductAndAssignToStore(Long storeId, Product product, List<MultipartFile> multipartFiles)
            throws IOException {
        // Retrieve the store from the repository
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + storeId));
        if (product.getCompositions() == null) {
            product.setCompositions(new HashSet<Composition>());
        }
        // if (product.getTags() == null) {
        //     product.setTags(new HashSet<Tag>());
        // }
        for (Composition composition : product.getCompositions()) {
            compositionRepository.save(composition);
            product.getCompositions().add(composition);
        }
        if (product.getQuantity() > 0)
            product.setAvailable(true);
        else
            product.setAvailable(false);
        if (product.getImageUrls() == null) {
            product.setImageUrls(new HashSet<String>());
        }
        multipartFiles.forEach(image -> {
            String imgUrl = objectStorageService.saveFileAlone((MultipartFile) image, FOLDER);
            product.getImageUrls().add(imgUrl);
        });
        for (Tag tag : product.getTags()) {
            Tag existingTag = tagRepository.findByName(tag.getName());
            if (existingTag != null) {
                // Use existing tag if found in the database
                product.getTags().add(existingTag);
            } else {
                // Create a new tag if not found in the database
                tagRepository.save(tag);
                product.getTags().add(tag);
            }
        }
        product.setStore(store);
        store.getProducts().add(product);
        storeRepository.save(store);

        return product;
    }

}
