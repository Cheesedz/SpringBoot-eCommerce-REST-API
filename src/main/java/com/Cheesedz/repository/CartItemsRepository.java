package com.Cheesedz.repository;

import com.Cheesedz.model.CartItems;
import com.Cheesedz.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    Optional<CartItems> findById(Long id);
    List<CartItems> findByCartID(Long cartId);
    List<Product> findByProductID(Long productId);
}
