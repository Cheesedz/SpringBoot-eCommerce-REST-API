package com.Cheesedz.service;

import com.Cheesedz.model.CartItems;
import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface CartItemsService {
    ResponseEntity<ResponseObject> getCartItem(Long id, Long userID);
    ResponseEntity<ResponseObject> addItem(CartItems newItem, Long userID);
    ResponseEntity<ResponseObject> updateItem(CartItems newItem, Long id, Long userID);
    ResponseEntity<ResponseObject> deleteItem(Long id, Long userID);
}
