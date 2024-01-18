package com.Cheesedz.service;

import com.Cheesedz.controller.ProductController;
import com.Cheesedz.model.Product;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ResponseObject> getAllProducts() {
        List<Product> foundProducts = productRepository.findAll();
        return foundProducts.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all products successfully", foundProducts)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any products", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> insertProduct(Product newProduct) {
        List<Product> foundProducts = productRepository.findByName(newProduct.getName().trim());
        if (foundProducts.size() > 0) {
            logger.info("Failed to insert data: " + newProduct);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Product name already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newProduct);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert product successfully", productRepository.save(newProduct))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateProduct(Product newProduct, Long id) {
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
                    product.setOrderID(newProduct.getOrderID());
                    product.setImgURL(newProduct.getImgURL());
                    return productRepository.save(product);
                }
        ).orElseGet(()-> productRepository.save(newProduct));
        logger.info("Update data successfully. " + newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product successfully", updatedProduct)
        );
    }

    public ResponseEntity<ResponseObject> deleteProduct(Long id) {
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

    //public
}
