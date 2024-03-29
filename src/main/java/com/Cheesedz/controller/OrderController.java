package com.Cheesedz.controller;

import com.Cheesedz.model.Order;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.OrderService;
import com.Cheesedz.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user/{userId}/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> getAllOrders(@PathVariable(name = "userId") Long userId) {
        return orderService.getAllOrders(userId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> getOrder(@PathVariable(name = "userId") Long userId, @PathVariable(name = "id") Long id) {
        return orderService.findById(id, userId);
    }

    @GetMapping("/{id}/products")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> getAllProducts(@PathVariable(name = "userId") Long userId, @PathVariable(name = "id") Long id) {
        return orderService.getAllProducts(id, userId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> addOrder(@RequestBody Order newProduct, @PathVariable(name = "userId") Long userId) {
        return orderService.addOrder(newProduct, userId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> updateOrder(@RequestBody Order newOrder,
                                                      @PathVariable(name = "userId") Long userId, @PathVariable(name = "id") Long id) {
        return orderService.updateOrder(newOrder, id, userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteOrder(@PathVariable(name = "userId") Long userId, @PathVariable(name = "id") Long id) {
        return orderService.deleteOrder(id, userId);
    }
}
