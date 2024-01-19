package com.Cheesedz.service;

import com.Cheesedz.controller.ProductController;
import com.Cheesedz.controller.VoucherController;
import com.Cheesedz.model.Order;
import com.Cheesedz.model.Voucher;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.OrderRepository;
import com.Cheesedz.repository.ProductRepository;
import com.Cheesedz.repository.VoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);
    @Autowired
    private VoucherRepository voucherRepository;

    public ResponseEntity<ResponseObject> getAllVouchers() {
        List<Voucher> foundVouchers = voucherRepository.findAll();
        return foundVouchers.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all vouchers successfully", foundVouchers)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any vouchers", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<Voucher> foundVouchers = voucherRepository.findById(id);
        return foundVouchers.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query voucher successfully", foundVouchers)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find voucher with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> insertVoucher(Voucher newVoucher) {
        Optional<Voucher> foundVouchers = voucherRepository.findById(newVoucher.getId());
        if (foundVouchers.isPresent()) {
            logger.info("Failed to insert data: " + newVoucher);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Voucher id already existed", "")
            );

        } else {
            logger.info("Insert data successfully. " + newVoucher);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert voucher successfully", voucherRepository.save(newVoucher))
            );

        }
    }

    public ResponseEntity<ResponseObject> updateVoucher(Voucher newVoucher, Long id) {
        Voucher updatedVoucher = voucherRepository.findById(id).map(
                voucher -> {
                    voucher.setUserID(newVoucher.getUserID());
                    voucher.setName(newVoucher.getName());
                    voucher.setDiscountAmount(newVoucher.getDiscountAmount());
                    voucher.setExpirationDate(newVoucher.getExpirationDate());
                    voucher.setMinimumOrderValue(newVoucher.getMinimumOrderValue());
                    voucher.setDescription(newVoucher.getDescription());
                    return voucherRepository.save(voucher);
                }
        ).orElseGet(()-> voucherRepository.save(newVoucher));
        logger.info("Update data successfully. " + newVoucher);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update voucher successfully", updatedVoucher)
        );
    }

    public ResponseEntity<ResponseObject> deleteVoucher(Long id) {
        boolean existed = voucherRepository.existsById(id);
        if (existed) {
            logger.info("Delete data successfully");
            voucherRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete voucher successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find voucher to delete", "")
            );
        }
    }
}
