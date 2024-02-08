package com.Cheesedz.controller;

import com.Cheesedz.model.Product;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.ProductService;
import com.Cheesedz.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/shop/{shopId}/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllProducts(@PathVariable(name = "shopId") Long shopId) {
        return productService.getAllProducts(shopId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProduct(@PathVariable(name = "shopId") Long shopId, @PathVariable(name = "id") Long id) {
        return productService.findById(id, shopId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> addProduct(@RequestBody Product newProduct, @PathVariable(name = "shopId") Long shopId) {
        return productService.addProduct(newProduct, shopId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable(name = "shopId") Long shopId, @PathVariable(name = "id") Long id) {
        return productService.updateProduct(newProduct, id, shopId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable(name = "shopId") Long shopId, @PathVariable(name = "id") Long id) {
        return productService.deleteProduct(id, shopId);
    }
}
