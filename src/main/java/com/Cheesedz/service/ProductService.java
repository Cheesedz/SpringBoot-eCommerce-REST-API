package com.Cheesedz.service;

import com.Cheesedz.model.Product;
import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ResponseObject> getAllProducts(Long shopID);
    ResponseEntity<ResponseObject> findById(Long id, Long shopID);
    ResponseEntity<ResponseObject> addProduct(Product newProduct, Long shopID);
    ResponseEntity<ResponseObject> updateProduct(Product newProduct, Long id, Long shopID);
    ResponseEntity<ResponseObject> deleteProduct(Long id, Long shopID);
}
