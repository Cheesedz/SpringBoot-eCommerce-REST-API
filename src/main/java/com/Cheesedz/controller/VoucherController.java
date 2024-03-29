package com.Cheesedz.controller;

import com.Cheesedz.model.Voucher;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.VoucherService;
import com.Cheesedz.service.impl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user/{userId}/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> getAllVouchers(@PathVariable(name = "userId") Long userId) {
        return voucherService.getAllVouchers(userId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> getVoucher(@PathVariable(name = "userId") Long userId,
                                                  @PathVariable(name = "id") Long id) {
        return voucherService.getVoucher(id, userId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> addVoucher(@PathVariable(name = "userId") Long userId,
                                                     @RequestBody Voucher newVoucher) {
        return voucherService.addVoucher(newVoucher, userId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateVoucher(@RequestBody Voucher newVoucher,
                                     @PathVariable(name = "userId") Long userId, @PathVariable(name = "id") Long id) {
        return voucherService.updateVoucher(newVoucher, id, userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteVoucher(@PathVariable(name = "userId") Long userId,
                                                     @PathVariable(name = "id") Long id) {
        return voucherService.deleteVoucher(id, userId);
    }
}
