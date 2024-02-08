package com.Cheesedz.service;

import com.Cheesedz.model.Voucher;
import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface VoucherService {
    ResponseEntity<ResponseObject> getAllVouchers(Long userID);
    ResponseEntity<ResponseObject> getVoucher(Long id, Long userID);
    ResponseEntity<ResponseObject> addVoucher(Voucher newVoucher, Long userId);
    ResponseEntity<ResponseObject> updateVoucher(Voucher newVoucher, Long id, Long userID);
    ResponseEntity<ResponseObject> deleteVoucher(Long id, Long userID);
}
