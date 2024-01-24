package com.Cheesedz.controller;

import com.Cheesedz.model.CartItems;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user/{userId}/cart/cartItems")
public class CartItemsController {
    @Autowired
    private CartItemsService cartItemsService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> getItem(@PathVariable(name = "userId") Long userId,
                                                  @PathVariable(name = "id") Long id) {
        return cartItemsService.getCartItem(id, userId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> addItem(@RequestBody CartItems newItem,
                                                        @PathVariable(name = "userId") Long userId) {
        return cartItemsService.addItem(newItem, userId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateItem(@RequestBody CartItems newItem,
                                     @PathVariable(name = "userId") Long userId, @PathVariable(name = "id") Long id) {
        return cartItemsService.updateItem(newItem, id, userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteItem(@PathVariable(name = "userId") Long userId,
                                                     @PathVariable(name = "id") Long id) {
        return cartItemsService.deleteItem(id, userId);
    }
}
