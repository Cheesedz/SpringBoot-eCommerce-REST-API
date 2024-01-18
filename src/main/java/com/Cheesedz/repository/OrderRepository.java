package com.Cheesedz.repository;

import com.Cheesedz.model.Order;
import com.Cheesedz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);

    List<Order> findByUserID(Long userId);
}
