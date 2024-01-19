package com.Cheesedz.controller;

import com.Cheesedz.model.Shop;
import com.Cheesedz.model.Voucher;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.ShopService;
import com.Cheesedz.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllVouchers() {
        return voucherService.getAllVouchers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getShop(@PathVariable(name = "id") Long id) {
        return voucherService.findById(id);
    }

    @PostMapping("/insert")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> insertShop(@RequestBody Voucher newVoucher) {
        return voucherService.insertVoucher(newVoucher);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateShop(@RequestBody Voucher newVoucher, @PathVariable(name = "id") Long id) {
        return voucherService.updateVoucher(newVoucher, id);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteShop(@PathVariable(name = "id") Long id) {
        return voucherService.deleteVoucher(id);
    }
}
