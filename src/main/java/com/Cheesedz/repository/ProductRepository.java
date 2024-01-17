package com.Cheesedz.repository;

import com.Cheesedz.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String productName);
    List<Product> findByOrderId(Long orderId);
}
