package com.Cheesedz.controller;

import com.Cheesedz.model.Shop;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllOrders() {
        return shopService.getAllShops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<ResponseObject> findAllProducts(@PathVariable Long id) {
        return shopService.findAllProducts(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertShop(@RequestBody Shop newShop) {
        return shopService.insertShop(newShop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateShop(@RequestBody Shop newShop, @PathVariable Long id) {
        return shopService.updateShop(newShop, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteShop(@PathVariable Long id) {
        return shopService.deleteShop(id);
    }
}
