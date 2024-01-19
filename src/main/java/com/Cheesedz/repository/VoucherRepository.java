package com.Cheesedz.repository;

import com.Cheesedz.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Optional<Voucher> findById(Long id);

    List<Voucher> findByUserID(Long userId);
}
