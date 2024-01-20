package com.Cheesedz.controller;

import com.Cheesedz.model.Cart;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user/{userId}/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getCart(@PathVariable(name = "userId") Long userId) {
        return cartService.getCart();
    }

    @GetMapping("/id")
    public ResponseEntity<ResponseObject> getCartByID(@PathVariable(name = "userId") Long userId,
                                                      @PathVariable(name = "id") Long id) {
        return cartService.findById(id);
    }

    @GetMapping("/cartItems")
    public ResponseEntity<ResponseObject> getAllItems(@PathVariable(name = "userId") Long userId,
                                                      @PathVariable(name = "id") Long id) {
        return cartService.findAllCartItems(id);
    }

    @GetMapping("/price")
    public ResponseEntity<ResponseObject> getTotalPrice(@PathVariable(name = "userId") Long userId,
                                                        @PathVariable(name = "id") Long id) {
        return cartService.getTotalPrice(id);
    }

    @PostMapping("/insert")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> insertCart(@RequestBody Cart newCart,
                                                     @PathVariable(name = "userId") Long userId) {
        return cartService.insertCart(newCart);
    }

    @PutMapping("/id")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateCart(@RequestBody Cart newCart,
                                                     @PathVariable(name = "userId") Long userId,
                                                     @PathVariable(name = "id") Long id) {
        return cartService.updateCart(newCart, id);
    }

    @DeleteMapping("/id")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteCart(@PathVariable(name = "userId") Long userId,
                                                     @PathVariable(name = "id") Long id) {
        return cartService.deleteCart(id);
    }
}
