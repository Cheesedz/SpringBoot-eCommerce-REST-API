package com.Cheesedz.repository;

import com.Cheesedz.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findById(Long shopID);
    List<Shop> findByName(String shopName);
}
