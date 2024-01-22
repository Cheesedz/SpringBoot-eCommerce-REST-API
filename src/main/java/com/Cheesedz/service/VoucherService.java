package com.Cheesedz.service;

import com.Cheesedz.controller.VoucherController;
import com.Cheesedz.model.Product;
import com.Cheesedz.model.Voucher;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.UserRepository;
import com.Cheesedz.repository.VoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);
    @Autowired
    private VoucherRepository voucherRepository;

    public ResponseEntity<ResponseObject> getAllVouchers(Long userID) {
        List<Voucher> foundVouchers = voucherRepository.findByUserID(userID);
        return foundVouchers.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all user's vouchers successfully", foundVouchers)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any vouchers", "")
                );
    }

    public ResponseEntity<ResponseObject> getVoucher(Long id, Long userID) {
        Voucher voucher = voucherRepository.findById(id).get();
        return voucher.getUserID().equals(userID) ?
            ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Get voucher successfully", voucher)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Voucher doesn't belong to user", "")
            );
    }

    public ResponseEntity<ResponseObject> addVoucher(Voucher newVoucher, Long userId) {
        Optional<Voucher> foundVouchers = voucherRepository.findById(newVoucher.getId());
        if (foundVouchers.isPresent()) {
            logger.info("Failed to insert data: " + newVoucher);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Voucher id already existed", "")
            );

        } else {
            newVoucher.setUserID(userId);
            logger.info("Insert data successfully. " + newVoucher);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Add voucher successfully", voucherRepository.save(newVoucher))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateVoucher(Voucher newVoucher, Long id, Long userID) {
        Voucher foundVoucher = voucherRepository.findById(id).get();
        if (foundVoucher.getUserID().equals(userID)) {
            foundVoucher.setUserID(newVoucher.getUserID());
            foundVoucher.setName(newVoucher.getName());
            foundVoucher.setDiscountAmount(newVoucher.getDiscountAmount());
            foundVoucher.setExpirationDate(newVoucher.getExpirationDate());
            foundVoucher.setMinimumOrderValue(newVoucher.getMinimumOrderValue());
            foundVoucher.setDescription(newVoucher.getDescription());
            voucherRepository.save(foundVoucher);
            logger.info("Update data successfully. " + newVoucher);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update voucher successfully", foundVoucher)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                new ResponseObject("failed", "Voucher doesn't not belong to user ", "")
        );
    }

    public ResponseEntity<ResponseObject> deleteVoucher(Long id, Long userID) {
        Voucher foundVoucher = voucherRepository.findById(id).get();
        if (foundVoucher == null) {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find voucher to delete", "")
            );
        }
        else {
            if (foundVoucher.getUserID().equals(userID)) {
                logger.info("Delete data successfully");
                voucherRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Delete voucher successfully", "")
                );
            } else {
                logger.info("Failed to delete data");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Voucher doesn't not belong to user", "")
                );
            }
        }
    }
}
