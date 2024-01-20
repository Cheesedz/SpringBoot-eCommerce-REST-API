package com.Cheesedz.controller;

import com.Cheesedz.model.Product;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/shop/{shopId}/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllProducts(@PathVariable(name = "shopId") Long shopId) {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProduct(@PathVariable(name = "shopId") Long shopId, @PathVariable(name = "id") Long id) {
        return productService.findById(id);
    }

    @PostMapping("/insert")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct, @PathVariable(name = "shopId") Long shopId) {
        return productService.insertProduct(newProduct);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable(name = "shopId") Long shopId, @PathVariable(name = "id") Long id) {
        return productService.updateProduct(newProduct, id);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable(name = "shopId") Long shopId, @PathVariable(name = "id") Long id) {
        return productService.deleteProduct(id);
    }
}
