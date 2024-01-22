package com.Cheesedz.service;

import com.Cheesedz.controller.ProductController;
import com.Cheesedz.model.Product;
import com.Cheesedz.model.Voucher;
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

    public ResponseEntity<ResponseObject> getAllProducts(Long shopID) {
        List<Product> foundProducts = productRepository.findByShopID(shopID);
        return foundProducts.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all products successfully", foundProducts)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any products", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id, Long shopID) {
        Product product = productRepository.findById(id).get();
        return product.getShopID().equals(shopID) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get product successfully", product)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Product doesn't belong to shop", "")
                );
    }

    public ResponseEntity<ResponseObject> addProduct(Product newProduct, Long shopID) {
        List<Product> foundProduct = productRepository.findByName(newProduct.getName().trim());
        if (foundProduct.size() > 0) {
            logger.info("Failed to insert data: " + newProduct);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Product name already existed", "")
            );
        }
        else {
            newProduct.setShopID(shopID);
            logger.info("Insert data successfully. " + newProduct);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Add product successfully", productRepository.save(newProduct))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateProduct(Product newProduct, Long id, Long shopID) {
        Product foundProduct = productRepository.findById(id).get();
        if (foundProduct.getShopID().equals(shopID)) {
            foundProduct.setName(newProduct.getName());
            foundProduct.setCategory(newProduct.getCategory());
            foundProduct.setDescription(newProduct.getDescription());
            foundProduct.setAvailable(newProduct.getAvailable());
            foundProduct.setPrice(newProduct.getPrice());
            foundProduct.setSold(newProduct.getSold());
            foundProduct.setRating(newProduct.getRating());
            foundProduct.setShopName(newProduct.getShopName());
            foundProduct.setOrderID(newProduct.getOrderID());
            foundProduct.setImgURL(newProduct.getImgURL());
            productRepository.save(foundProduct);
            logger.info("Update data successfully. " + newProduct);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update product successfully", foundProduct)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                new ResponseObject("failed", "Product doesn't not belong to shop ", "")
        );
    }

    public ResponseEntity<ResponseObject> deleteProduct(Long id, Long shopID) {
        Product foundProduct = productRepository.findById(id).get();
        if (foundProduct == null) {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find voucher to delete", "")
            );
        }
        else {
            if (foundProduct.getShopID().equals(shopID)) {
                logger.info("Delete data successfully");
                productRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Delete product successfully", "")
                );
            } else {
                logger.info("Failed to delete data");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Product doesn't not belong to user", "")
                );
            }
        }
    }
}
