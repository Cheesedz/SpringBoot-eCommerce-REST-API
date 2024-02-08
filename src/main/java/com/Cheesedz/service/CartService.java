package com.Cheesedz.service;

import com.Cheesedz.model.Cart;
import com.Cheesedz.model.CartItems;
import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<ResponseObject> getCart(Long userID);
    ResponseEntity<ResponseObject> findById(Long id, Long userID);
    ResponseEntity<ResponseObject> getAllCartItems(Long id, Long userID);
    ResponseEntity<ResponseObject> getTotalPrice(Long id, Long userID);
    ResponseEntity<ResponseObject> addCart(Cart newCart, Long userID);
    ResponseEntity<ResponseObject> updateCart(Cart newCart, Long id, Long userID);
    ResponseEntity<ResponseObject> deleteCart(Long id, Long userID);
}
