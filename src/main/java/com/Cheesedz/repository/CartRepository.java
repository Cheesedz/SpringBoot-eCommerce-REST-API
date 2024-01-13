package com.Cheesedz.repository;

import com.Cheesedz.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @NonNull
    @Query("SELECT p FROM product p WHERE p.id = ?1")
    Optional<Cart> findByCartId(@NonNull Long cartID);
}
