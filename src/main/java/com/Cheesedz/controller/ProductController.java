package com.Cheesedz.controller;

import com.Cheesedz.config.ProductConfig;
import com.Cheesedz.model.Product;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query product successfully", foundProduct)
            ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find product with id = " + id, "")
        );
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        List<Product> foundProducts = productRepository.findByName(newProduct.getName().trim());
        if (foundProducts.size() > 0) {
            logger.info("Failed to insert data: " + newProduct);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("Failed", "Product name already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newProduct);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert product successfully", productRepository.save(newProduct))
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = productRepository.findById(id).map(
                product -> {
                    product.setName(newProduct.getName());
                    product.setCategory(newProduct.getCategory());
                    product.setDescription(newProduct.getDescription());
                    product.setAvailable(newProduct.getAvailable());
                    product.setPrice(newProduct.getPrice());
                    product.setSold(newProduct.getSold());
                    product.setRating(newProduct.getRating());
                    product.setShopName(newProduct.getShopName());
                    product.setImgURL(newProduct.getImgURL());
                    return productRepository.save(product);
                }
        ).orElseGet(()->{
            return productRepository.save(newProduct);
        });
        logger.info("Update data successfully. " + newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product successfully", updatedProduct)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean existed = productRepository.existsById(id);
        if (existed) {
            logger.info("Delete data successfully");
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find product to delete", "")
            );
        }
    }
}
