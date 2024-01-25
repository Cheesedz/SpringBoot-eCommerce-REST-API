package com.Cheesedz.controller;

import com.Cheesedz.model.Shop;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @GetMapping("")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getShop(@PathVariable(name = "id") Long id) {
        return shopService.findById(id);
    }

    @GetMapping("/{id}/all-products")
    public ResponseEntity<ResponseObject> getAllProducts(@PathVariable(name = "id") Long id) {
        return shopService.findAllProducts(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> addShop(@RequestBody Shop newShop) {
        return shopService.addShop(newShop);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateShop(@RequestBody Shop newShop, @PathVariable(name = "id") Long id) {
        return shopService.updateShop(newShop, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteShop(@PathVariable(name = "id") Long id) {
        return shopService.deleteShop(id);
    }
}
