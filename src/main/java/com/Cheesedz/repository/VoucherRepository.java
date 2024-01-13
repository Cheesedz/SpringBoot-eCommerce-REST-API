package com.Cheesedz.repository;

import com.Cheesedz.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    @NonNull
    @Query("SELECT v FROM voucher v WHERE v.id = ?1")
    Optional<Voucher> findByVoucherId(@NonNull Long voucherID);
}
