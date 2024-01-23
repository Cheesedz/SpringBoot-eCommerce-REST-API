package com.Cheesedz.controller;

import com.Cheesedz.exception.ResponseEntityErrorException;
import com.Cheesedz.model.Cart;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user/{userId}/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @ExceptionHandler(ResponseEntityErrorException.class)
    public ResponseEntity<ResponseObject> handleExceptions(ResponseEntityErrorException exception) {
        return exception.getApiResponse();
    }
    @GetMapping("")
    public ResponseEntity<ResponseObject> getCart(@PathVariable(name = "userId") Long userId) {
        return cartService.getCart(userId);
    }

    @GetMapping("/id")
    public ResponseEntity<ResponseObject> getCartByID(@PathVariable(name = "userId") Long userId,
                                                      @PathVariable(name = "id") Long id) {
        return cartService.findById(id, userId);
    }

    @GetMapping("/cartItems")
    public ResponseEntity<ResponseObject> getAllItems(@PathVariable(name = "userId") Long userId,
                                                      @PathVariable(name = "id") Long id) {
        return cartService.getAllCartItems(id, userId);
    }

    @GetMapping("/price")
    public ResponseEntity<ResponseObject> getTotalPrice(@PathVariable(name = "userId") Long userId,
                                                        @PathVariable(name = "id") Long id) {
        return cartService.getTotalPrice(id, userId);
    }

    @PostMapping("/insert")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> addCart(@RequestBody Cart newCart,
                                                     @PathVariable(name = "userId") Long userId) {
        return cartService.addCart(newCart, userId);
    }

    @PutMapping("/id")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateCart(@RequestBody Cart newCart,
                                                     @PathVariable(name = "userId") Long userId,
                                                     @PathVariable(name = "id") Long id) {
        return cartService.updateCart(newCart, id, userId);
    }

    @DeleteMapping("/id")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteCart(@PathVariable(name = "userId") Long userId,
                                                     @PathVariable(name = "id") Long id) {
        return cartService.deleteCart(id, userId);
    }
}
