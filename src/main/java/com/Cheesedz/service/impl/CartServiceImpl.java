package com.Cheesedz.service.impl;

import com.Cheesedz.controller.CartController;
import com.Cheesedz.model.Cart;
import com.Cheesedz.model.CartItems;
import com.Cheesedz.model.Product;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.CartItemsRepository;
import com.Cheesedz.repository.CartRepository;
import com.Cheesedz.repository.ProductRepository;
import com.Cheesedz.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartServiceImpl implements CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    public ResponseEntity<ResponseObject> getCart(Long userID) {
        List<Cart> foundCart = cartRepository.findByUserID(userID);
        return foundCart.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get user's cart successfully", foundCart)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any cart", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id, Long userID) {
        Cart foundCart = cartRepository.findById(id).get();
        return foundCart.getUserID().equals(userID) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query cart successfully", foundCart)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cart doesn't belong to user ", "")
                );
    }

    public ResponseEntity<ResponseObject> getAllCartItems(Long id, Long userID) {
        Cart cart = cartRepository.findById(id).get();
        return cart.getUserID().equals(userID) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get cart's items successfully", cartItemsRepository.findByCartID(id))
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cart doesn't belong to user", "")
                );
    }

    public ResponseEntity<ResponseObject> getTotalPrice(Long id, Long userID) {
        Cart foundCart = cartRepository.findById(id).get();
        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);
        if (foundCart.getUserID().equals(userID)) {
            List<CartItems> cartItems = cartItemsRepository.findByCartID(id);
            cartItems.forEach(
                    item -> {
                        List<Product> foundProducts = productRepository.findByCartItemID(item.getId());
                        totalPrice.updateAndGet(v -> v + foundProducts.get(0).getPrice() * item.getQuantity());
                    }
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Calculate total price successfully", totalPrice)
        );
    }

    public ResponseEntity<ResponseObject> addCart(Cart newCart, Long userID) {
        Optional<Cart> foundOrders = cartRepository.findById(newCart.getId());
        if (foundOrders.isPresent()) {
            logger.info("Failed to insert data: " + newCart);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Cart id already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newCart);
            newCart.setUserID(userID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Add cart successfully", cartRepository.save(newCart))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateCart(Cart newCart, Long id, Long userID) {
        Cart foundCart = cartRepository.findById(id).get();
        if (foundCart.getUserID().equals(userID)) {
            foundCart.setUserID(newCart.getUserID());
            cartRepository.save(foundCart);
            logger.info("Update data successfully. " + newCart);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update cart successfully", foundCart)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                new ResponseObject("failed", "Cart doesn't not belong to user ", "")
        );
    }

    public ResponseEntity<ResponseObject> deleteCart(Long id, Long userID) {
        Cart foundCart = cartRepository.findById(id).get();
        if (foundCart.getUserID().equals(userID)) {
            logger.info("Delete data successfully");
            cartRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete cart successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find cart to delete", "")
            );
        }
    }
}
