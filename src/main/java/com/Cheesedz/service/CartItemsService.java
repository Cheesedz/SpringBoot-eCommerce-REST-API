package com.Cheesedz.service;

import com.Cheesedz.controller.CartItemsController;
import com.Cheesedz.model.CartItems;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.CartItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemsService {
    private static final Logger logger = LoggerFactory.getLogger(CartItemsController.class);
    @Autowired
    private CartItemsRepository cartItemsRepository;

    public ResponseEntity<ResponseObject> getAllItems() {
        List<CartItems> foundItems = cartItemsRepository.findAll();
        return foundItems.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all items successfully", foundItems)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any items", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<CartItems> foundProduct = cartItemsRepository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query item successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find item with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> insertItem(CartItems newItem) {
        List<CartItems> foundItems = cartItemsRepository.findByCartID(newItem.getCartID());
        if (foundItems.size() > 0) {
            logger.info("Failed to insert data: " + newItem);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Product name already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newItem);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert item successfully", cartItemsRepository.save(newItem))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateItem(CartItems newItem, Long id) {
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

    public ResponseEntity<ResponseObject> deleteItem(Long id) {
        boolean existed = cartItemsRepository.existsById(id);
        if (existed) {
            logger.info("Delete data successfully");
            cartItemsRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete item successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find item to delete", "")
            );
        }
    }
}
