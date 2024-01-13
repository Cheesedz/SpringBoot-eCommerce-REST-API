package com.Cheesedz.repository;

import com.Cheesedz.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Product, Long> {

}
