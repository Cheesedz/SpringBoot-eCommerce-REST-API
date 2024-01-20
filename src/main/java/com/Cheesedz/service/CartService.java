package com.Cheesedz.service;

import com.Cheesedz.controller.CartController;
import com.Cheesedz.model.Cart;
import com.Cheesedz.model.CartItems;
import com.Cheesedz.model.Product;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.CartItemsRepository;
import com.Cheesedz.repository.CartRepository;
import com.Cheesedz.repository.ProductRepository;
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
public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    public ResponseEntity<ResponseObject> getCart() {
        List<Cart> foundOrders = cartRepository.findAll();
        return foundOrders.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get user's cart successfully", foundOrders)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any cart", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<Cart> foundOrders = cartRepository.findById(id);
        return foundOrders.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query cart successfully", foundOrders)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find cart with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> findAllCartItems(Long id) {
        Optional<Cart> foundOrder = cartRepository.findById(id);
        return foundOrder.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query cart's items successfully",
                                cartItemsRepository.findByCartID(id))
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find cart with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> getTotalPrice(Long id) {
        Optional<Cart> foundOrder = cartRepository.findById(id);
        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);
        if (foundOrder.isPresent()) {
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

    public ResponseEntity<ResponseObject> insertCart(Cart newCart) {
        Optional<Cart> foundOrders = cartRepository.findById(newCart.getId());
        if (foundOrders.isPresent()) {
            logger.info("Failed to insert data: " + newCart);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Cart id already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newCart);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert cart successfully", cartRepository.save(newCart))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateCart(Cart newCart, Long id) {
        Cart updatedCart = cartRepository.findById(id).map(
                cart -> {
                    cart.setUserID(newCart.getUserID());
                    return cartRepository.save(cart);
                }
        ).orElseGet(()-> cartRepository.save(newCart));
        logger.info("Update data successfully. " + newCart);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update order successfully", updatedCart)
        );
    }

    public ResponseEntity<ResponseObject> deleteCart(Long id) {
        boolean existed = cartRepository.existsById(id);
        if (existed) {
            logger.info("Delete data successfully");
            cartRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete order successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find order to delete", "")
            );
        }
    }
}
