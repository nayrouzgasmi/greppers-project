package tn.esprit.pidev.Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Services.ProductService;
import tn.esprit.pidev.Util.LocalDateDeserializer;
import tn.esprit.pidev.Util.ObjectStorage;

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

    @PostMapping(value= "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> createProductAndAssignToStore(@PathVariable("id") long id,
            @RequestParam("product") String productJson,
            @RequestParam(value = "file", required = false) List<MultipartFile> images)
            throws IOException {
        // ObjectMapper objectMapper = new ObjectMapper();
        // try {
        // ObjectMapper objectMapper = new ObjectMapper();
        // Gson gson = new GsonBuilder()
        // .registerTypeAdapter(LocalDate.class, new LocalDateTimeDeserializere())
        // .create();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        // Gson gson = new Gson();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(module);

        // Product product = gson.fromJson(productJson, Product.class);
        Product product = objectMapper.readValue(productJson, Product.class);
        System.out.println(product.toString());
        Product createdProduct = productService.createProductAndAssignToStore(id, product, images);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }
        // return new ResponseEntity<>(null, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductAndAssignToStore(@PathVariable("id") long id,
            @RequestParam(required = false, name = "product", value = "product") String productJson,
            @RequestParam(value = "file", required = false) List<MultipartFile> images) {
        try {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
            // Gson gson = new Gson();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(module);
    
            // Product product = gson.fromJson(productJson, Product.class);
            Product product = objectMapper.readValue(productJson, Product.class);
            System.out.println(product.toString());
            Product createdProduct = productService.createProductAndAssignToStore(id, product, images);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);

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
