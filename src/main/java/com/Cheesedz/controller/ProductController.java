package com.Cheesedz.controller;

import com.Cheesedz.model.Product;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        return productService.insertProduct(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return productService.updateProduct(newProduct, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
