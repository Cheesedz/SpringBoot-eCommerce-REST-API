package com.Cheesedz.service.impl;

import com.Cheesedz.controller.CartItemsController;
import com.Cheesedz.model.Cart;
import com.Cheesedz.model.CartItems;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.CartItemsRepository;
import com.Cheesedz.repository.CartRepository;
import com.Cheesedz.service.CartItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartItemsServiceImpl implements CartItemsService {
    private static final Logger logger = LoggerFactory.getLogger(CartItemsController.class);
    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private CartRepository cartRepository;

    public ResponseEntity<ResponseObject> getCartItem(Long id, Long userID) {
        CartItems foundItem = cartItemsRepository.findById(id).get();
        Cart foundCart = (Cart) cartRepository.findByUserID(userID);
        return foundCart.getId().equals(foundItem.getCartID()) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query item successfully", foundItem)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cart item doesn't belong to user", "")
                );
    }

    public ResponseEntity<ResponseObject> addItem(CartItems newItem, Long userID) {
        CartItems foundItem = cartItemsRepository.findById(newItem.getId()).get();
        Cart foundCart = (Cart) cartRepository.findByUserID(userID);
        if (!foundCart.getId().equals(foundItem.getCartID())) {
            logger.info("Failed to insert data: " + newItem);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Cannot add item into user's cart", "")
            );
        } else {
            logger.info("Insert data successfully. " + newItem);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Add item successfully", cartItemsRepository.save(newItem))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateItem(CartItems newItem, Long id, Long userID) {
        CartItems updatedItem = cartItemsRepository.findById(id).map(
                item -> {
                    item.setCartID(newItem.getCartID());
                    item.setProductID(newItem.getProductID());
                    item.setQuantity(newItem.getQuantity());
                    return cartItemsRepository.save(item);
                }
        ).orElseGet(()-> cartItemsRepository.save(newItem));
        logger.info("Update data successfully. " + newItem);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update item successfully", updatedItem)
        );
    }

    public ResponseEntity<ResponseObject> deleteItem(Long id, Long userID) {
        CartItems foundItem = cartItemsRepository.findById(id).get();
        Cart foundCart = (Cart) cartRepository.findByUserID(userID);
        if (foundCart.getId().equals(foundItem.getCartID())) {
            logger.info("Delete data successfully");
            cartItemsRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete item successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cart item doesn't belong to user", "")
            );
        }
    }
}
