package com.Cheesedz.controller;

import com.Cheesedz.model.Product;
import com.Cheesedz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("")
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }
}
