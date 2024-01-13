package com.Cheesedz.repository;

import com.Cheesedz.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Product, Long> {
    @NonNull
    @Query("SELECT o FROM order o WHERE o.id = ?1")
    Optional<Product> findByOrderId(@NonNull Long orderID);
}
