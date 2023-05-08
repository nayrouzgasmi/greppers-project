package tn.esprit.pidev.Controllers;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Services.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.findAll();
            return new ResponseEntity<>(products, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> createProductAndAssignToStore(@PathVariable("id") long id,
            @RequestParam("product") String productJson, @RequestParam(value = "file", required = false) List<MultipartFile> images)
            throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                Product product = objectMapper.readValue(productJson, Product.class);
        Product createdProduct = productService.createProductAndAssignToStore(id, product, images);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PostMapping("/img")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void saveFile(@RequestParam(value = "file", required = true) MultipartFile image) {
        // Product createdProduct = productService.saveFileAlone( image);
        // System.out.println(image.toString());
        try {
            productService.saveFileAlone(image);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // return new ResponseEntity<>(, HttpStatus.CREATED);
    }

    @PostMapping("/imgs")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void saveFiles(@RequestParam(value = "file", required = true) List<MultipartFile> images) {
        images.forEach(image -> {
            try {
                productService.saveFileAlone((MultipartFile) image);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/ref/{id}")
    public ResponseEntity<Void> deleteProductWithReference(@PathVariable("id") long id) {
        productService.deleteProductWithReference(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
