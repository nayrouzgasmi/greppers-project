package tn.esprit.pidev.Controllers;

import tn.esprit.pidev.Entities.ProductCategory;
import tn.esprit.pidev.Entities.ProductSubcategory;
import tn.esprit.pidev.Services.ProductCategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("")
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        try {
            List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
            return new ResponseEntity<>(productCategories, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable int id) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        return new ResponseEntity<>(productCategory, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        ProductCategory createdProductCategory = productCategoryService.saveProductCategory(productCategory);
        return new ResponseEntity<>(createdProductCategory, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductCategory> saveSubcategoryWithCategory(@PathVariable("id") long id,
            @RequestBody ProductSubcategory productSubcategory) {
                ProductCategory savedProductSubcategory = productCategoryService
                .saveSubcategoryWithCategory(id,productSubcategory);
        return new ResponseEntity<>(savedProductSubcategory, HttpStatus.CREATED);
    }


    // @PutMapping("/{id}")
    // public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable
    // int id,
    // @RequestBody ProductCategory productCategory) {
    // ProductCategory updatedProductCategory =
    // productCategoryService.updateProductCategory(id, productCategory);
    // return new ResponseEntity<>(updatedProductCategory, HttpStatus.OK);
    // }
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> assignSubcategoryToCategory(@PathVariable Long id,
            @RequestBody ProductSubcategory productSubcategory) {
        ProductCategory updatedProductCategory = productCategoryService.findProductCategoryByIdAndAssignCategory(id,
                productSubcategory);
        return new ResponseEntity<>(updatedProductCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable int id) {
        productCategoryService.deleteProductCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
