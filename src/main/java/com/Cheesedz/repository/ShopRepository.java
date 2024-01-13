package com.Cheesedz.repository;

import com.Cheesedz.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    @NonNull
    @Query("SELECT s FROM shop s WHERE s.id = ?1")
    Optional<Shop> findByShopId(@NonNull Long shopID);
}
